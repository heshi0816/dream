package com.dream.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";  // 重定向到 /index，进一步触发安全重定向到登录
    }
}
