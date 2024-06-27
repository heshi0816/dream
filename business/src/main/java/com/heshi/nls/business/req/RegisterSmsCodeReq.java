package com.heshi.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterSmsCodeReq {

    @NotBlank(message = "【手机号】不能为空")
    private String mobile;
}
