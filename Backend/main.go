package main

import (
	"database/sql"
	"encoding/binary"
	"encoding/json"
	"errors"
	"fmt"
	"log"
	"net"
	"net/http"
	"os"
	"runtime"
	"strconv"
	"time"

	"github.com/strosel/goinput/httpin"

	_ "github.com/go-sql-driver/mysql"
)

var (
	db  *sql.DB
	err error
)

func main() {
	f, err := os.OpenFile("C:\\Users\\TBTE4\\Documents\\Exjobb\\Backend\\log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		log.Fatalf("error opening file: %v", err)
	}
	defer f.Close()
	log.SetOutput(f)

	db, err = sql.Open("mysql", "root:@tcp(localhost:3306)/robochef")
	if err != nil {
		log.Fatalf("DB Open Error %v", err)
	}

	err = db.Ping()
	if err != nil {
		log.Fatalf("DB Ping Error %v", err)
	}

	mux := http.NewServeMux()
	mux.HandleFunc("/locations", getLocations)
	mux.HandleFunc("/location", getLocation)
	mux.HandleFunc("/order", sendOrder)
	mux.HandleFunc("/MenuItems", getMenuItems)

	err = http.ListenAndServe(":8080", mux)
	if err != nil {
		log.Fatalf("http.ListenAndServe Error %v", err)
	}
}

func getLocations(w http.ResponseWriter, r *http.Request) {
	ctx := r.Context()

	select {
	case <-ctx.Done():
		log.Println(ctx.Err().Error())
	default:
		w.Header().Set("Content-Type", "application/json")

		// query
		rows, err := db.Query("SELECT * FROM locations")
		if HTTPError(err, w, 500) {
			return
		}

		var target string
		var Locations Locations
		for rows.Next() {
			var Loc Location
			err = rows.Scan(&Loc.ID, &Loc.Name, &Loc.Lat, &Loc.Long, &target)
			if HTTPError(err, w, 500) {
				return
			}
			conn, err := net.Dial("tcp", target)
			if HTTPError(err, w, 500) {
				return
			}
			Loc.IsOnline = Ping(conn, time.Second*10)
			Locations.Locations = append(Locations.Locations, Loc)
		}

		b, err := json.Marshal(Locations)
		if HTTPError(err, w, 500) {
			return
		}

		w.Write(b)
	}
}

func getLocation(w http.ResponseWriter, r *http.Request) {
	ctx := r.Context()

	select {
	case <-ctx.Done():
		log.Println(ctx.Err().Error())
	default:
		w.Header().Set("Content-Type", "application/json")

		id, err := httpin.GetParamInt(r, "id")
		if HTTPError(err, w, 400) {
			return
		}

		// query
		sql := fmt.Sprintf(`
		SELECT L.name, I.id, I.name, I.img, M.price 
		FROM locations L 
		JOIN locationmenu M ON M.locationID = L.id 
		JOIN menuitems I ON I.id = M.menuItem
		WHERE L.id=%v`, id)
		rows, err := db.Query(sql)
		if HTTPError(err, w, 500) {
			return
		}

		var Loc Location
		for rows.Next() {
			var MI MenuItem
			err = rows.Scan(&Loc.Name, &MI.ID, &MI.Name, &MI.Img, &MI.Price)
			if HTTPError(err, w, 500) {
				return
			}
			Loc.Menu = append(Loc.Menu, MI)
		}

		b, err := json.Marshal(Loc)
		if HTTPError(err, w, 500) {
			return
		}

		w.Write(b)
	}
}

func sendOrder(w http.ResponseWriter, r *http.Request) {
	ctx := r.Context()

	select {
	case <-ctx.Done():
		log.Println(ctx.Err().Error())
	default:
		w.Header().Set("Content-Type", "application/json")

		r.ParseForm()
		log.Println(r.Body)
		loc, err := strconv.ParseUint(r.Form.Get("loc"), 10, 64)
		if HTTPError(err, w, 400) {
			return
		}

		var orders []uint64
		// orders is a slice of uints in a repeating pattern of id, volume
		err = json.Unmarshal([]byte(r.Form.Get("orders")), &orders)
		if HTTPError(err, w, 400) {
			return
		}

		// query for target ip
		sql := fmt.Sprintf(`SELECT target FROM locations WHERE id=%v LIMIT 1`, loc)
		rows, err := db.Query(sql)
		if HTTPError(err, w, 500) {
			return
		}

		var target string
		for rows.Next() {
			err = rows.Scan(&target)
			if HTTPError(err, w, 500) {
				return
			}
		}

		//write order to target an recieve order number and deadline
		c, err := net.Dial("tcp", target)
		if HTTPError(err, w, 500) {
			return
		}
		defer c.Close()

		// send follows the pattern [size, location, orders]
		send := []byte{}

		//allocate space for size
		component := make([]byte, 64)
		send = append(send, component...)

		//add loc
		binary.PutUvarint(component, loc)
		send = append(send, component...)

		//add orders
		for _, order := range orders {
			component = make([]byte, 64)
			binary.PutUvarint(component, order)
			send = append(send, component...)
		}

		//insert size
		component = make([]byte, 64)
		binary.PutUvarint(component, uint64(len(send)))
		for i := range component {
			send[i] = component[i]
		}

		_, err = c.Write(send)
		if HTTPError(err, w, 500) {
			return
		}

		recive := make([]byte, 128)
		_, err = c.Read(recive)
		if HTTPError(err, w, 500) {
			return
		}

		oid, n := binary.Uvarint(recive[:64])
		if n <= 0 {
			w.WriteHeader(500)
			log.Println("Can't Read bytes [:64]")
			return
		}
		dl, n := binary.Varint(recive[64:])
		if n <= 0 {
			w.WriteHeader(500)
			log.Println("Can't Read bytes [64:]")
			return
		}

		b, err := json.Marshal(Order{
			ID:       uint(oid),
			Deadline: dl,
		})
		if HTTPError(err, w, 500) {
			return
		}

		w.Write(b)
	}
}

func getMenuItems(w http.ResponseWriter, r *http.Request) {
	ctx := r.Context()

	select {
	case <-ctx.Done():
		log.Println(ctx.Err().Error())
	default:
		w.Header().Set("Content-Type", "application/json")

		keys, ok := r.URL.Query()["key"]
		if !ok {
			HTTPError(errors.New("No keys"), w, 400)
			return
		}

		// query
		sql := "SELECT * FROM menuitems WHERE "
		for i, key := range keys {
			if i < len(keys)-1 {
				sql += fmt.Sprintf("id = %v OR", key)
			} else {
				sql += fmt.Sprintf("id = %v", key)
			}

		}

		rows, err := db.Query(sql)
		if HTTPError(err, w, 500) {
			return
		}

		var MenuItems []MenuItem
		for rows.Next() {
			var MI MenuItem
			err = rows.Scan(&MI.ID, &MI.Name, &MI.Img)
			if HTTPError(err, w, 500) {
				return
			}
			MenuItems = append(MenuItems, MI)
		}

		b, err := json.Marshal(&struct {
			Menu []MenuItem `json:"menu"`
		}{MenuItems})
		if HTTPError(err, w, 500) {
			return
		}

		w.Write(b)
	}
}

//HTTPError Check error. If error is not nil log it and set the given status code
func HTTPError(err error, w http.ResponseWriter, statusCode int) bool {
	if err != nil {
		w.WriteHeader(statusCode)
		_, _file, _line, _ := runtime.Caller(1)
		loc := fmt.Sprintf("(%v:%v)", _file, _line)
		log.Println(err, loc)
		return true
	}
	return false
}

//Ping Ping Connection with custom handshake
func Ping(c net.Conn, timeout time.Duration) bool {
	select {
	case <-time.After(timeout):
		m := []byte{0xf0, 0x0d}

		_, err := c.Write(m)
		if err != nil {
			return false
		}

		r := make([]byte, 1)
		_, err = c.Read(r)
		if err != nil {
			return false
		}

		if r[0] == 0xdd {
			return true
		}
	}
	return false
}
