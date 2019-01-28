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
	ID     int        `json:"id,omitempty"`
	Name   string     `json:"name"`
	Lat    float64    `json:"latitude,omitempty"`
	Long   float64    `json:"longitude,omitempty"`
	Target string     `json:"target,omitempty"`
	Menu   []MenuItem `json:"menu,omitempty"`
}

//Order An order
type Order struct {
	ID       uint  `json:"id"`
	Deadline int64 `json:"deadline"`
}
