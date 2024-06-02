package com.dream.nusblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello world post " + name;
    }
}
