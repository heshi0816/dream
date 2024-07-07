package com.heshi.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.github.pagehelper.PageHelper;
import com.heshi.nls.business.context.LoginMemberContext;
import com.heshi.nls.business.domain.Filetrans;
import com.heshi.nls.business.domain.FiletransExample;
import com.heshi.nls.business.enums.FiletransPayStatusEnum;
import com.heshi.nls.business.enums.FiletransStatusEnum;
import com.heshi.nls.business.enums.OrderInfoOrderTypeEnum;
import com.heshi.nls.business.exception.BusinessException;
import com.heshi.nls.business.exception.BusinessExceptionEnum;
import com.heshi.nls.business.mapper.FiletransMapper;
import com.heshi.nls.business.nls.NlsUtil;
import com.heshi.nls.business.req.FiletransPayReq;
import com.heshi.nls.business.req.FiletransQueryReq;
import com.heshi.nls.business.req.OrderInfoPayReq;
import com.heshi.nls.business.resp.FiletransQueryResp;
import com.heshi.nls.business.resp.OrderInfoPayResp;
import com.heshi.nls.business.util.VodUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Date now = new Date();
        Filetrans filetrans = new Filetrans();
        filetrans.setId(id);
        filetrans.setPayStatus(FiletransPayStatusEnum.S.getCode());
        filetrans.setStatus(FiletransStatusEnum.SUBTITLE_INIT.getCode());
        filetrans.setUpdatedAt(now);
        filetransMapper.updateByPrimaryKeySelective(filetrans);

        log.info("发起语音识别任务");
        Filetrans filetransDB = filetransMapper.selectByPrimaryKey(id);
        CommonResponse commonResponse = NlsUtil.trans(filetransDB.getAudio(), filetransDB.getLang());
        if (commonResponse.getHttpStatus() == 200) {
            JSONObject result = JSONObject.parseObject(commonResponse.getData());
            Integer statusCode = result.getInteger("StatusCode");
            String statusText = result.getString("StatusText");
            String taskId = result.getString("TaskId");
            if ("SUCCESS".equals(statusText)) {
                log.info("录音文件识别请求成功响应： " + result.toJSONString());
            } else {
                log.error("录音文件识别请求失败： " + result.toJSONString());
                throw new BusinessException(BusinessExceptionEnum.FILETRANS_TRANS_ERROR);
            }

            log.info("更新语音识别状态为：生成字幕中");
            Filetrans filetransAfterNls = new Filetrans();
            filetransAfterNls.setId(id);
            filetransAfterNls.setStatus(FiletransStatusEnum.SUBTITLE_PENDING.getCode());
            filetransAfterNls.setUpdatedAt(now);
            filetransAfterNls.setTaskId(taskId);
            filetransAfterNls.setTransTime(now);
            filetransAfterNls.setTransStatusCode(statusCode);
            filetransAfterNls.setTransStatusText(statusText);
            filetransMapper.updateByPrimaryKeySelective(filetransAfterNls);
        }
    }

    public void afterTrans(JSONObject jsonResult) {
        Date now = new Date();
        String taskId = jsonResult.getString("TaskId");
        Integer statusCode = jsonResult.getInteger("StatusCode");
        String statusText = jsonResult.getString("StatusText");

        Filetrans filetrans = new Filetrans();
        filetrans.setUpdatedAt(now);
        filetrans.setTransStatusCode(statusCode);
        filetrans.setTransStatusText(statusText);

        if ("21050000".equals(statusCode.toString())) {
            filetrans.setSolveTime(new Date(jsonResult.getLong("SolveTime")));
            filetrans.setStatus(FiletransStatusEnum.SUBTITLE_SUCCESS.getCode());
        } else {
            filetrans.setStatus(FiletransStatusEnum.SUBTITLE_FAILURE.getCode());
        }

        FiletransExample filetransExample = new FiletransExample();
        filetransExample.createCriteria().andTaskIdEqualTo(taskId).andStatusEqualTo(FiletransStatusEnum.SUBTITLE_PENDING.getCode());
        filetransMapper.updateByExampleSelective(filetrans, filetransExample);
    }

    public List<FiletransQueryResp> query(FiletransQueryReq req) {

        FiletransExample filetransExample = new FiletransExample();
        FiletransExample.Criteria criteria = filetransExample.createCriteria();

        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        if (StrUtil.isNotBlank(req.getLang())) {
            criteria.andLangEqualTo(req.getLang());
        }
        if (StrUtil.isNotBlank(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }

        filetransExample.setOrderByClause("id desc");

        PageHelper.startPage(2, 2);
        List<Filetrans> filetransList = filetransMapper.selectByExample(filetransExample);
        return BeanUtil.copyToList(filetransList, FiletransQueryResp.class);
    }
}
