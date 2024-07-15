package com.heshi.nls.business.controller.admin;

import cn.hutool.crypto.digest.DigestUtil;
import com.heshi.nls.business.req.UserLoginReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.UserLoginResp;
import com.heshi.nls.business.service.KaptchaService;
import com.heshi.nls.business.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/user")
public class WebUserController {

    @Resource
    private UserService userService;

    @Resource
    private KaptchaService kaptchaService;

//    password: a111111
    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));

        log.info("用户登录开始：{}", req.getLoginName());

        // 校验图片验证码，防止机器人
        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        UserLoginResp userLoginResp = userService.login(req);

        return new CommonResp<>(userLoginResp);
    }

}
