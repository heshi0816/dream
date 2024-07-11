package com.heshi.nls.business.controller.web;

import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.StatisticResp;
import com.heshi.nls.business.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/report")
public class WebReportController {

    @Resource
    private ReportService reportService;

    /**
     * 首页数字统计
     */
    @GetMapping("/query-statistic")
    public CommonResp<StatisticResp> queryStatistic() {
        StatisticResp resp = reportService.queryStatistic();
        return new CommonResp<>(resp);
    }

}
