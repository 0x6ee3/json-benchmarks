package main

import (
	"net/http"

	"gopkg.in/gin-gonic/gin.v1"
)

type Message map[string]string

func main() {
	router := gin.New()

	router.GET("/", func(c *gin.Context) {
		c.Header("Server", "go-gin")
		c.String(http.StatusOK, "Hello World!")
	})
	router.GET("/json", func(c *gin.Context) {
		c.Header("Server", "go-gin")
		m := Message{"message": "Hello World!"}
		c.JSON(200, m)
	})

	router.Run(":8000")
}
