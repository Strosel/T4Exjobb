package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
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
		log.Fatal(err)
	}

	mux := http.NewServeMux()
	mux.HandleFunc("/locations", getLocations)
	mux.HandleFunc("/location", getLocation)
	http.ListenAndServe(":8080", mux)
}

func getLocations(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	// query
	rows, err := db.Query("SELECT * FROM locations")
	if HttpError(err, w, 500) {
		return
	}

	var Locations []Location
	for rows.Next() {
		var Loc Location
		err = rows.Scan(&Loc.ID, &Loc.Name, &Loc.Lat, &Loc.Long)
		if HttpError(err, w, 500) {
			return
		}
		Locations = append(Locations, Loc)
	}

	b, err := json.Marshal(Locations)
	if HttpError(err, w, 500) {
		return
	}

	w.Write(b)
}

func getLocation(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	id, err := httpin.GetParamInt(r, "id")
	if HttpError(err, w, 400) {
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
	if HttpError(err, w, 500) {
		return
	}

	var Loc Location
	for rows.Next() {
		var MI MenuItem
		err = rows.Scan(&Loc.Name, &MI.ID, &MI.Name, &MI.Img, &MI.Price)
		if HttpError(err, w, 500) {
			return
		}
		Loc.Menu = append(Loc.Menu, MI)
	}

	b, err := json.Marshal(Loc)
	if HttpError(err, w, 500) {
		return
	}

	w.Write(b)
}

func HttpError(err error, w http.ResponseWriter, statusCode int) bool {
	if err != nil {
		w.WriteHeader(statusCode)
		log.Println(err)
		return true
	}
	return false
}
