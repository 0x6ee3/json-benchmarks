package main

import (
	"github.com/valyala/fasthttp"
    "encoding/json"
)

type Message map[string]string

func main() {
    fasthttp.ListenAndServe(":8000", func(ctx *fasthttp.RequestCtx) {
        ctx.Response.Header.Set("Server", "go-fasthttp")

        switch string(ctx.Path()) {
        case "/json":
	        m := Message{"message": "Hello World!"}
	        b, _ := json.Marshal(m)
	        ctx.SetBody(b)
        default:
	        ctx.SetBody([]byte("Hello World!"))
        }
    })
}

