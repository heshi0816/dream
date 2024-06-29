package com.heshi.nls.business.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberLoginReq {

    /**
     * 手机号
     */
    @NotBlank(message = "【手机号】不能为空")
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "【密码】不能为空")
    private String password;

}
