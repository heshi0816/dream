package com.dream.nusblog.service;

import com.dream.nusblog.domain.Demo;
import com.dream.nusblog.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Demo> list() {
        return ebookMapper.selectByExample(null);
    }
}
