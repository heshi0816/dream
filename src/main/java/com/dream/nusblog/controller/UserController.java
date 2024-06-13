package com.dream.nusblog.controller;

import com.dream.nusblog.req.UserQueryReq;
import com.dream.nusblog.req.UserSaveReq;
import com.dream.nusblog.resp.PageResp;
import com.dream.nusblog.resp.UserQueryResp;
import com.dream.nusblog.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.dream.nusblog.resp.CommonResp;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }
}
