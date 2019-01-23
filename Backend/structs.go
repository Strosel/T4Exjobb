package main

type MenuItem struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Img   string `json:"img"`
	Price int    `json:"price"`
}

type Location struct {
	ID   int        `json:"id,omitempty"`
	Name string     `json:"name"`
	Lat  float64    `json:"latitude,omitempty"`
	Long float64    `json:"longitude,omitempty"`
	Menu []MenuItem `json:"menu,omitempty"`
}
