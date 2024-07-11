package com.heshi.nls.business.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticResp {

    /**
     * 实时在线
     */
    private Integer onlineCount;

    /**
     * 注册人数
     */
    private Integer registerCount;

    /**
     * 语音识别数
     */
    private Integer filetransCount;

    /**
     * 语音识别时长，秒
     */
    private Integer filetransSecond;

    /**
     * 订单数
     */
    private Integer orderCount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
}
