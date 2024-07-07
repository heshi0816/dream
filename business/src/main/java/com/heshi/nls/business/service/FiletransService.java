package com.heshi.nls.business.service;

import cn.hutool.core.util.IdUtil;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.domain.Filetrans;
import com.heshi.nls.business.enums.FiletransPayStatusEnum;
import com.heshi.nls.business.enums.FiletransStatusEnum;
import com.heshi.nls.business.enums.OrderInfoOrderTypeEnum;
import com.heshi.nls.business.mapper.FiletransMapper;
import com.heshi.nls.business.req.FiletransPayReq;
import com.heshi.nls.business.req.OrderInfoPayReq;
import com.heshi.nls.business.resp.OrderInfoPayResp;
import com.heshi.nls.business.util.VodUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class FiletransService {

    @Resource
    private FiletransMapper filetransMapper;

    @Resource
    private OrderInfoService orderInfoService;

    public OrderInfoPayResp pay(FiletransPayReq req) throws Exception {
        Date now = new Date();

        // 获取视频信息
        GetVideoInfoResponse videoInfo = VodUtil.getVideoInfo(req.getVod());
        Float duration = videoInfo.getVideo().getDuration();
        log.info("视频：{}，时长：{}", req.getVod(), duration);
        int second = Math.round(duration);

        Filetrans filetrans = new Filetrans();
        long id = IdUtil.getSnowflakeNextId();
        filetrans.setId(id);
        filetrans.setMemberId(LoginMemberContext.getId());
        filetrans.setName(req.getName());
        filetrans.setSecond(second);
        filetrans.setAmount(req.getAmount());
        filetrans.setAudio(req.getAudio());
        filetrans.setFileSign(req.getFileSign());
        filetrans.setPayStatus(FiletransPayStatusEnum.I.getCode());
        filetrans.setStatus(FiletransStatusEnum.INIT.getCode());
        filetrans.setLang(req.getLang());
        filetrans.setVod(req.getVod());
        // filetrans.setTaskId();
        // filetrans.setTransStatusCode();
        // filetrans.setTransStatusText();
        // filetrans.setTransTime();
        // filetrans.setSolveTime();
        filetrans.setCreatedAt(now);
        filetrans.setUpdatedAt(now);

        filetransMapper.insert(filetrans);

        // 保存订单信息
        OrderInfoPayReq orderInfoPayReq = new OrderInfoPayReq();
        orderInfoPayReq.setOrderType(OrderInfoOrderTypeEnum.FILETRANS_PAY.getCode());
        // 订单表的info保存语音识别表的id
        orderInfoPayReq.setInfo(String.valueOf(id));
        orderInfoPayReq.setAmount(req.getAmount());
        orderInfoPayReq.setChannel(req.getChannel());
        orderInfoPayReq.setDesc("语音识别付费");
        return orderInfoService.pay(orderInfoPayReq);

    }

    /**
     * 支付成功后处理
     */
    public void afterPaySuccess(Long id) {
        Filetrans filetrans = new Filetrans();
        filetrans.setId(id);
        filetrans.setPayStatus(FiletransPayStatusEnum.S.getCode());
        filetrans.setStatus(FiletransStatusEnum.SUBTITLE_INIT.getCode());
        filetrans.setUpdatedAt(new Date());
        filetransMapper.updateByPrimaryKeySelective(filetrans);
    }
}
