package com.heshi.nls.business.service;

import com.heshi.nls.business.domain.Demo;
import com.heshi.nls.business.domain.DemoExample;
import com.heshi.nls.business.mapper.DemoMapper;
import com.heshi.nls.business.mapper.cust.DemoMapperCust;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapperCust demoMapperCust;
    @Resource
    private DemoMapper demoMapper;

    public int count() {
        return demoMapperCust.count();
        // return Math.toIntExact(demoMapper.countByExample(null));
    }

    public List<Demo> query(String mobile) {
        DemoExample demoExample = new DemoExample();
        demoExample.setOrderByClause("id asc");
        DemoExample.Criteria criteria = demoExample.createCriteria();
        if (mobile != null) {
            criteria.andMobileEqualTo(mobile);
        }
        List<Demo> list = demoMapper.selectByExample(demoExample);
        return list;
    }
}
