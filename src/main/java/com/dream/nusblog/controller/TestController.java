package com.dream.nusblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Controller
public class TestController {
    @Value("${test.hello : TEST}")
    private String testHello;

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);


    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/hello")
    public String hello() {
        return "hello world" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello world post " + name;
    }

    @RequestMapping("/redis/set/{key}/{value}")
    public String set(@PathVariable Long key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value, 3600, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success";
    }

    @RequestMapping("/redis/get/{key}")
    public Object get(@PathVariable Long key) {
        Object object = redisTemplate.opsForValue().get(key);
        LOG.info("key: {}, value: {}", key, object);
        return object;
    }
}
