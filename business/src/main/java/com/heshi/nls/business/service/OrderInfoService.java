package com.heshi.nls.business.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.heshi.nls.business.alipay.AliPayService;
import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.domain.OrderInfo;
import com.heshi.nls.business.enums.OrderInfoChannelEnum;
import com.heshi.nls.business.enums.OrderInfoStatusEnum;
import com.heshi.nls.business.exception.BusinessException;
import com.heshi.nls.business.exception.BusinessExceptionEnum;
import com.heshi.nls.business.mapper.OrderInfoMapper;
import com.heshi.nls.business.req.OrderInfoPayReq;
import com.heshi.nls.business.resp.OrderInfoPayResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private AliPayService aliPayService;

    public OrderInfoPayResp pay(OrderInfoPayReq req) {
        Date now = new Date();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(IdUtil.getSnowflakeNextId());
        String orderNo = genOrderNo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setOrderAt(now);
        orderInfo.setOrderType(req.getOrderType());
        orderInfo.setInfo(req.getInfo());
        orderInfo.setMemberId(LoginMemberContext.getId());
        orderInfo.setAmount(req.getAmount());
        orderInfo.setPayAt(now);
        orderInfo.setChannel(req.getChannel());
        orderInfo.setChannelAt(null);
        orderInfo.setStatus(OrderInfoStatusEnum.I.getCode());
        orderInfo.setDesc(req.getDesc());
        orderInfo.setCreatedAt(now);
        orderInfo.setUpdatedAt(now);
        orderInfoMapper.insert(orderInfo);

        // 请求支付宝接口
        OrderInfoPayResp orderInfoPayResp = new OrderInfoPayResp();
        orderInfoPayResp.setOrderNo(orderNo);

        // 请求支付宝接口
        if (OrderInfoChannelEnum.ALIPAY.getCode().equals(req.getChannel())) {
            // 调用支付宝下单接口
            AlipayTradePagePayResponse response = aliPayService.pay(req.getDesc(), orderNo, req.getAmount().toPlainString());
            orderInfoPayResp.setChannelResult(response.getBody());
            return orderInfoPayResp;
        } else {
            log.warn("支付渠道【{}】不存在", req.getChannel());
            throw new BusinessException(BusinessExceptionEnum.PAY_ERROR);
        }
    }

    public String genOrderNo() {
        String no = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        int random = (int) (Math.random() * 900 + 100);
        no = no + random;
        return no;
    }
}
