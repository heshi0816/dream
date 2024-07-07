package com.heshi.nls.business.req;

import lombok.Data;

@Data
public class FiletransQueryReq {

    private Long memberId;

    private String lang;

    private String status;

    private String name;
}
