package com.heshi.nls.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heshi.nls.business.domain.FiletransSubtitle;
import com.heshi.nls.business.domain.FiletransSubtitleExample;
import com.heshi.nls.business.mapper.FiletransSubtitleMapper;
import com.heshi.nls.business.req.FiletransSubtitleQueryReq;
import com.heshi.nls.business.resp.FiletransSubtitleQueryResp;
import com.heshi.nls.business.resp.PageResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FiletransSubtitleService {

    @Resource
    FiletransSubtitleMapper filetransSubtitleMapper;

    @Transactional
    public void saveSubtitle(Long filetransId, JSONObject result) {
        Date now = new Date();
        // 先清空
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        filetransSubtitleExample.createCriteria().andFiletransIdEqualTo(filetransId);
        filetransSubtitleMapper.deleteByExample(filetransSubtitleExample);

        JSONArray sentences = result.getJSONArray("Sentences");
        for (int i = 0; i < sentences.size(); i++) {
            JSONObject sentence = (JSONObject) sentences.get(i);
            String text = sentence.getString("Text");
            Integer beginTime = sentence.getInteger("BeginTime");
            Integer endTime = sentence.getInteger("EndTime");

            FiletransSubtitle filetransSubtitle = new FiletransSubtitle();
            filetransSubtitle.setId(IdUtil.getSnowflakeNextId());
            filetransSubtitle.setFiletransId(filetransId);
            filetransSubtitle.setIndex(i+1);
            filetransSubtitle.setBegin(beginTime);
            filetransSubtitle.setEnd(endTime);
            filetransSubtitle.setText(text);
            filetransSubtitle.setCreatedAt(now);
            filetransSubtitle.setUpdatedAt(now);
            filetransSubtitleMapper.insert(filetransSubtitle);
        }
    }

    public PageResp<FiletransSubtitleQueryResp> query(FiletransSubtitleQueryReq req) {
        FiletransSubtitleExample filetransSubtitleExample = new FiletransSubtitleExample();
        FiletransSubtitleExample.Criteria criteria = filetransSubtitleExample.createCriteria();

        criteria.andFiletransIdEqualTo(req.getFiletransId());

        filetransSubtitleExample.setOrderByClause("`index` asc");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<FiletransSubtitle> filetransSubtitleList = filetransSubtitleMapper.selectByExample(filetransSubtitleExample);
        // 构造分页返回信息
        PageResp<FiletransSubtitleQueryResp> pageResp = new PageResp<>();
        // 获取分页信息，只需要总数
        PageInfo<FiletransSubtitle> pageInfo = new PageInfo<>(filetransSubtitleList);
        pageResp.setTotal(pageInfo.getTotal());
        // 获取当前分页列表内容
        List<FiletransSubtitleQueryResp> filetransSubtitleQueryRespList = BeanUtil.copyToList(filetransSubtitleList, FiletransSubtitleQueryResp.class);
        pageResp.setList(filetransSubtitleQueryRespList);

        return pageResp;
    }
}
