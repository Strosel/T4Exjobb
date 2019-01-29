package main

import (
	"database/sql"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"log"
	"net"
	"net/http"
	"os"

	"github.com/strosel/goinput/httpin"

	_ "github.com/go-sql-driver/mysql"
)

var (
	db  *sql.DB
	err error
)

func main() {
	f, err := os.OpenFile("log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
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

	err = http.ListenAndServe(":8080", mux)
	if err != nil {
		log.Fatalf("http.ListenAndServeTLS Error %v", err)
	}
}

func getLocations(w http.ResponseWriter, r *http.Request) {
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
		// --ToDo--
		// ping target set online status
		// --ToDo--
		Locations.Locations = append(Locations.Locations, Loc)
	}

	b, err := json.Marshal(Locations)
	if HTTPError(err, w, 500) {
		return
	}

	w.Write(b)
}

func getLocation(w http.ResponseWriter, r *http.Request) {
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

func sendOrder(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	id, err := httpin.GetParamInt(r, "id")
	if HTTPError(err, w, 400) {
		return
	}
	loc, err := httpin.GetParamInt(r, "location")
	if HTTPError(err, w, 400) {
		return
	}

	// query
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

	send := make([]byte, 64)
	binary.PutUvarint(send, uint64(id))
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

//HTTPError Check error. If error is not nil log it and set the given status code
func HTTPError(err error, w http.ResponseWriter, statusCode int) bool {
	if err != nil {
		w.WriteHeader(statusCode)
		log.Println(err)
		return true
	}
	return false
}
