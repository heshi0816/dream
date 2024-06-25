package com.heshi.nls.business.controller;

import com.heshi.nls.business.req.DemoQueryReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.DemoQueryResp;
import com.heshi.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private DemoService demoService;

    @GetMapping("/hello")
    public CommonResp<String> hello() {
        // return "Hello World!!!";
        return new CommonResp<>("Hello World!!!");
    }

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        // return demoService.count();
        return new CommonResp<>(demoService.count());
    }

    @GetMapping("/query")
    public CommonResp<List<DemoQueryResp>> query(DemoQueryReq req) {
        List<DemoQueryResp> demoList = demoService.query(req);
        // CommonResp<List<Demo>> commonResp = new CommonResp<>();
        // commonResp.setContent(demoList);
        // return commonResp;

        return new CommonResp<>(demoList);
    }
}
