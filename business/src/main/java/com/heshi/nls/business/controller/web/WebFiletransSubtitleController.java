package com.heshi.nls.business.controller.web;

import com.heshi.nls.business.req.FiletransSubtitleQueryReq;
import com.heshi.nls.business.req.GenSubtitleReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.FiletransSubtitleQueryResp;
import com.heshi.nls.business.resp.PageResp;
import com.heshi.nls.business.service.FiletransSubtitleService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web/filetrans-subtitle")
public class WebFiletransSubtitleController {

    @Resource
    private FiletransSubtitleService filetransSubtitleService;

    @GetMapping("/query")
    public CommonResp<PageResp<FiletransSubtitleQueryResp>> query(@Valid FiletransSubtitleQueryReq req)  {
        PageResp<FiletransSubtitleQueryResp> pageResp = filetransSubtitleService.query(req);
        return new CommonResp<>(pageResp);
    }


    @GetMapping("/gen-subtitle")
    public CommonResp<PageResp<FiletransSubtitleQueryResp>> genSubtitle(@Valid GenSubtitleReq req)  {
        filetransSubtitleService.genSubtitle(req);
        return new CommonResp<>();
    }
}
