package com.heshi.nls.business.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.domain.OrderInfo;
import com.heshi.nls.business.enums.OrderInfoStatusEnum;
import com.heshi.nls.business.mapper.OrderInfoMapper;
import com.heshi.nls.business.req.OrderInfoPayReq;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    public void pay(OrderInfoPayReq req) {
        Date now = new Date();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(IdUtil.getSnowflakeNextId());
        orderInfo.setOrderNo(genOrderNo());
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
    }

    public String genOrderNo() {
        String no = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        int random = (int) (Math.random() * 900 + 100);
        no = no + random;
        return no;
    }
}
