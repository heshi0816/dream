package com.heshi.nls.business.controller.web;

import cn.hutool.crypto.digest.DigestUtil;
import com.heshi.nls.business.enums.SmsCodeUseEnum;
import com.heshi.nls.business.req.MemberLoginReq;
import com.heshi.nls.business.req.MemberRegisterReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.MemberLoginResp;
import com.heshi.nls.business.service.KaptchaService;
import com.heshi.nls.business.service.MemberService;
import com.heshi.nls.business.service.SmsCodeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web/member")
public class WebMemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private SmsCodeService smsCodeService;

    @Resource
    private KaptchaService kaptchaService;

    @PostMapping("/register")
    public CommonResp<Object> register(@Valid @RequestBody MemberRegisterReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        log.info("会员注册开始：{}", req.getMobile());

        smsCodeService.validCode(req.getMobile(), SmsCodeUseEnum.REGISTER.getCode(), req.getCode());
        log.info("注册验证码校验通过：{}", req.getMobile());

        memberService.register(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        // 校验图片验证码，防止机器人
        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());


        log.info("会员登录开始：{}", req.getMobile());
        MemberLoginResp memberLoginResp = memberService.login(req);

        return new CommonResp<>(memberLoginResp);
    }
}
