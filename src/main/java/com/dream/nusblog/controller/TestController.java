package com.dream.nusblog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {
    @Value("${test.hello : TEST}")
    private String testHello;

    @GetMapping("/hello")
    public String hello() {
        return "hello world" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello world post " + name;
    }
}
