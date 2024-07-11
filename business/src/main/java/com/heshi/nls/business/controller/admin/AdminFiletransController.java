package com.heshi.nls.business.controller.admin;

import com.heshi.nls.business.req.FiletransQueryReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.FiletransQueryResp;
import com.heshi.nls.business.resp.PageResp;
import com.heshi.nls.business.service.FiletransService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/filetrans")
public class AdminFiletransController {

    @Resource
    private FiletransService filetransService;

    @GetMapping("/query")
    public CommonResp<PageResp<FiletransQueryResp>> query(@Valid FiletransQueryReq req)  {
        PageResp<FiletransQueryResp> pageResp = filetransService.query(req);
        return new CommonResp<>(pageResp);
    }
}
