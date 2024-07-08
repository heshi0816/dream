package com.heshi.nls.business.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heshi.nls.business.domain.FiletransSubtitle;
import com.heshi.nls.business.domain.FiletransSubtitleExample;
import com.heshi.nls.business.mapper.FiletransSubtitleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
}
