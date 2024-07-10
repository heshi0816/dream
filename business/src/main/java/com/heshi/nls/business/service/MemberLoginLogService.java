package com.heshi.nls.business.service;

import cn.hutool.core.util.IdUtil;
import com.heshi.nls.business.domain.MemberLoginLog;
import com.heshi.nls.business.mapper.MemberLoginLogMapper;
import com.heshi.nls.business.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class MemberLoginLogService {
    @Resource
    MemberLoginLogMapper memberLoginLogMapper;

    public void save(MemberLoginResp memberLoginResp) {
        log.info("增加会员登录日志：{}", memberLoginResp);
        Date now = new Date();
        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setId(IdUtil.getSnowflakeNextId());
        memberLoginLog.setMemberId(memberLoginResp.getId());
        memberLoginLog.setLoginTime(now);
        memberLoginLog.setToken(memberLoginResp.getToken());
        memberLoginLog.setHeartCount(0);
        memberLoginLog.setLastHeartTime(now);
        memberLoginLogMapper.insert(memberLoginLog);
    }
}
