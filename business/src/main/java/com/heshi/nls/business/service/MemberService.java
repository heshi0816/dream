package com.heshi.nls.business.service;

import cn.hutool.core.collection.CollUtil;
import com.heshi.nls.business.domain.Member;
import com.heshi.nls.business.domain.MemberExample;
import com.heshi.nls.business.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    /**
     * 按手机号查会员信息
     * @param mobile
     * @return
     */
    public Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
