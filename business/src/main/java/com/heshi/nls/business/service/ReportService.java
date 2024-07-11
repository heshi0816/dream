package com.heshi.nls.business.service;

import com.heshi.nls.business.mapper.cust.ReportMapperCust;
import com.heshi.nls.business.resp.StatisticResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Resource
    private ReportMapperCust reportMapperCust;

    /**
     * 首页数字统计
     */
    public StatisticResp queryStatistic() {
        StatisticResp statisticResp = new StatisticResp();
        statisticResp.setOnlineCount(reportMapperCust.queryOnlineCount());
        statisticResp.setRegisterCount(reportMapperCust.queryRegisterCount());
        statisticResp.setFiletransCount(reportMapperCust.queryFiletransCount());
        statisticResp.setFiletransSecond(reportMapperCust.queryFiletransSecond());
        statisticResp.setOrderCount(reportMapperCust.queryOrderCount());
        statisticResp.setOrderAmount(reportMapperCust.queryOrderAmount());
        return statisticResp;
    }

}
