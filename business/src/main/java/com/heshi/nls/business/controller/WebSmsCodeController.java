package com.heshi.nls.business.controller;

import com.heshi.nls.business.enums.SmsCodeUseEnum;
import com.heshi.nls.business.req.RegisterSmsCodeReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.service.SmsCodeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/sms-code")
public class WebSmsCodeController {

    @Resource
    private SmsCodeService smsCodeService;


    //@RequestBody allows the input to be a json body, @Valid allows the limit you grant on the field in request
    @PostMapping("/send-for-register")
    public CommonResp<Object> sendForRegister(@Valid @RequestBody RegisterSmsCodeReq req) {
        smsCodeService.sendCode(req.getMobile(), SmsCodeUseEnum.REGISTER.getCode());
        return new CommonResp<>();
    }
}
