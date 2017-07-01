package com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/json", produces = {"application/json"})
    public Message sayHelloJson() {
        Message msg = new Message();
        msg.message = "Hello World!";

        return msg;
    }

    public class Message {
        public String message;
    }
}
