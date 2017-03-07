package main

import (
	"encoding/json"
	"net/http"
)

type Message map[string]string

func serveHello(w http.ResponseWriter, req *http.Request) {
	w.Header().Set("Server", "Go-Raw")
	w.Write([]byte("Hello World!"))
}

func serveJson(w http.ResponseWriter, req *http.Request) {
	w.Header().Set("Server", "Go-Raw")
	m := Message{"message": "Hello World!"}
	b, _ := json.Marshal(m)
	w.Write(b)
}

func main() {
	http.HandleFunc("/", serveHello)
	http.HandleFunc("/json", serveJson)
	http.ListenAndServe(":8000", nil)
}
