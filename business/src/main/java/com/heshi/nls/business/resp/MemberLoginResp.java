package com.heshi.nls.business.resp;


import lombok.Data;

@Data
public class MemberLoginResp {

    private String name;

    private String token;

    private Long id;
}