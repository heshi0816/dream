package com.heshi.nls.business.controller.web;

import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.req.FiletransPayReq;
import com.heshi.nls.business.req.FiletransQueryReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.FiletransQueryResp;
import com.heshi.nls.business.resp.OrderInfoPayResp;
import com.heshi.nls.business.resp.PageResp;
import com.heshi.nls.business.service.FiletransService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/web/filetrans")
public class WebFiletransController {
    @Resource
    private FiletransService filetransService;

    @PostMapping("/pay")
    public CommonResp<OrderInfoPayResp> pay(@Valid @RequestBody FiletransPayReq req) throws Exception {
        log.info("语音识别支付开始");
        OrderInfoPayResp result = filetransService.pay(req);
        log.info("语音识别支付结束");
        return new CommonResp<>(result);
    }

    @GetMapping("/query")
    public CommonResp<PageResp<FiletransQueryResp>> query(@Valid FiletransQueryReq req)  {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<FiletransQueryResp> pageResp = filetransService.query(req);
        return new CommonResp<>(pageResp);
    }
}
