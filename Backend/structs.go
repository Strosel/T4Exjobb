package main

//MenuItem A menu item
type MenuItem struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Img   string `json:"img"`
	Price int    `json:"price"`
}

//Location A store location
type Location struct {
	ID       int        `json:"id,omitempty"`
	Name     string     `json:"name"`
	Lat      float64    `json:"latitude,omitempty"`
	Long     float64    `json:"longitude,omitempty"`
	IsOnline bool       `json:"isOnline"`
	Menu     []MenuItem `json:"menu,omitempty"`
}

//Locations A list of store locations
type Locations struct {
	Locations []Location `json:"locations"`
}

//Order An order
type Order struct {
	ID       uint   `json:"id"`
	Deadline uint64 `json:"deadline"`
}
