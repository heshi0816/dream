package com.heshi.nls.business.controller;

import com.heshi.nls.business.domain.Demo;
import com.heshi.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Resource
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!";
    }

    @GetMapping("/count")
    public int count() {
        return demoService.count();
    }

    @GetMapping("/query")
    public List<Demo> query(String mobile) {
        return demoService.query(mobile);
    }
}
