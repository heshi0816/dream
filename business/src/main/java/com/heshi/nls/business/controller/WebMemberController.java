package com.heshi.nls.business.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.heshi.nls.business.req.MemberRegisterReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/member")
public class WebMemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public CommonResp<Object> register(@Valid @RequestBody MemberRegisterReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword()));
        memberService.register(req);
        return new CommonResp<>();
    }
}
