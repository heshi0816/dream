package com.heshi.nls.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BusinessExceptionEnum {
    DEMO_MOBILE_NOT_NULL("手机号不能为空！！!"),
    SMS_CODE_TOO_FREQUENT("短信请求过于频繁"),
    SMS_CODE_ERROR("短信验证码不正确"),
    SMS_CODE_EXPIRED("短信验证码未发送或已过期，请重新发送短信"),
    IMAGE_CODE_ERROR("图片验证码不正确或已过期"),

    MEMBER_MOBILE_HAD_REGISTER("手机号已注册"),
    MEMBER_LOGIN_ERROR("手机号未注册或密码错误"),

;
    @Getter
    private final String desc;
}

