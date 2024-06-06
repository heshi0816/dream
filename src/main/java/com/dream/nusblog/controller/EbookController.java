package com.dream.nusblog.controller;

import com.dream.nusblog.req.EbookReq;
import com.dream.nusblog.resp.CommonResp;
import com.dream.nusblog.resp.EbookResp;
import com.dream.nusblog.resp.PageResp;
import com.dream.nusblog.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}

