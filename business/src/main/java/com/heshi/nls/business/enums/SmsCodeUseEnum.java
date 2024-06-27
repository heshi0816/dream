package com.heshi.nls.business.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SmsCodeUseEnum {

    REGISTER("0", "注册"),
    FORGET_PASSWORD("1", "忘记密码");

    @Getter
    private String code;

    @Getter
    private String desc;

}
