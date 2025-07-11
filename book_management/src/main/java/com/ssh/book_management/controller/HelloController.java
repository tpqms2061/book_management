package com.ssh.book_management.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @GetMapping
    public String sayHello() {
        return "hello";
    }

    @PostMapping("/post")
    public String sayPostHello() {
        return "post hello";
    }

    @PutMapping("/put")
    public String sayPutHello() {
        return "put hello";
    }

    @DeleteMapping("/delete")
    public String sayDeleteHello() {
        return "delete hello";
    }
}
