package com.heshi.nls.business.service;

import cn.hutool.core.util.IdUtil;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.domain.Filetrans;
import com.heshi.nls.business.enums.FiletransPayStatusEnum;
import com.heshi.nls.business.enums.FiletransStatusEnum;
import com.heshi.nls.business.mapper.FiletransMapper;
import com.heshi.nls.business.req.FiletransPayReq;
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

    public void pay(FiletransPayReq req) throws Exception {
        Date now = new Date();
        GetVideoInfoResponse videoInfo = VodUtil.getVideoInfo(req.getVod());
        Float duration = videoInfo.getVideo().getDuration();
        log.info("视频：{}，时长：{}", req.getVod(), duration);
        int second = Math.round(duration);

        Filetrans filetrans = new Filetrans();
        filetrans.setId(IdUtil.getSnowflakeNextId());
        filetrans.setMemberId(LoginMemberContext.getId());
        filetrans.setMemberId(req.getMemberId());
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
    }
}
