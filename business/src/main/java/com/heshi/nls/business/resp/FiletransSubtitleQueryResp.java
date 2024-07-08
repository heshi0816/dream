package com.heshi.nls.business.resp;

import lombok.Data;

import java.util.Date;

@Data
public class FiletransSubtitleQueryResp {
    private Long id;

    private Long filetransId;

    private Integer index;

    private Integer begin;

    private Integer end;

    private String text;

    private Date createdAt;

    private Date updatedAt;

}
