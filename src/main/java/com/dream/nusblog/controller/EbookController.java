package com.dream.nusblog.controller;

import com.dream.nusblog.req.EbookQueryReq;
import com.dream.nusblog.req.EbookSaveReq;
import com.dream.nusblog.resp.EbookQueryResp;
import com.dream.nusblog.resp.PageResp;
import com.dream.nusblog.service.EbookService;
import com.jiawa.wiki.resp.CommonResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
