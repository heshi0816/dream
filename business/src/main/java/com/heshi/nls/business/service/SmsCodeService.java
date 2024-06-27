package com.heshi.nls.business.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.heshi.nls.business.domain.Member;
import com.heshi.nls.business.domain.SmsCode;
import com.heshi.nls.business.domain.SmsCodeExample;
import com.heshi.nls.business.enums.SmsCodeStatusEnum;
import com.heshi.nls.business.enums.SmsCodeUseEnum;
import com.heshi.nls.business.exception.BusinessException;
import com.heshi.nls.business.exception.BusinessExceptionEnum;
import com.heshi.nls.business.mapper.SmsCodeMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class SmsCodeService {

    @Resource
    private SmsCodeMapper smsCodeMapper;

    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @param use 用途
     */
    @Resource
    private MemberService memberService;

    public void sendCodeForRegister(String mobile) {
        Member member = memberService.selectByMobile(mobile);
        if (member != null) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_HAD_REGISTER);
        }
        sendCode(mobile, SmsCodeUseEnum.REGISTER.getCode());
    }

    private void sendCode(String mobile, String use) {
        Date now = new Date();
        String code = RandomUtil.randomNumbers(6);

        log.info("当前时间：", new Date());
        log.info("1分钟以前：", DateUtil.offsetMinute(new Date(), -1));

        SmsCodeExample smsCodeExample = new SmsCodeExample();
        SmsCodeExample.Criteria criteria = smsCodeExample.createCriteria();
        criteria.andMobileEqualTo(mobile).andUseEqualTo(use).andCreatedAtGreaterThan(DateUtil.offsetMinute(new Date(), -1));
        long count = smsCodeMapper.countByExample(smsCodeExample);
        if (count > 0) {
            throw new BusinessException(BusinessExceptionEnum.SMS_CODE_TOO_FREQUENT);
        }


        // 保存数据库
        SmsCode smsCode = new SmsCode();
        smsCode.setId(IdUtil.getSnowflakeNextId());
        smsCode.setMobile(mobile);
        smsCode.setCode(code);
        smsCode.setUse(use);
        smsCode.setStatus(SmsCodeStatusEnum.NOT_USED.getCode());
        smsCode.setCreatedAt(now);
        smsCode.setUpdatedAt(now);
        smsCodeMapper.insert(smsCode);

        // 对接短信通道，发送短信
    }
}
