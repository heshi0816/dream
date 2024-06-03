package com.dream.nusblog.service;

import com.dream.nusblog.domain.Ebook;
import com.dream.nusblog.domain.EbookExample;
import com.dream.nusblog.mapper.EbookMapper;
import com.dream.nusblog.req.EbookReq;
import com.dream.nusblog.resp.EbookResp;
import com.dream.nusblog.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        return list;
    }
}
