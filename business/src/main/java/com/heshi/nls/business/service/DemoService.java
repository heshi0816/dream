package com.heshi.nls.business.service;

import com.heshi.nls.business.mapper.DemoMapper;
import com.heshi.nls.business.mapper.cust.DemoMapperCust;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Resource
    private DemoMapperCust demoMapperCust;
    @Resource
    private DemoMapper demoMapper;

    public int count() {
        // return demoMapperCust.count();
        return Math.toIntExact(demoMapper.countByExample(null));
    }
}
