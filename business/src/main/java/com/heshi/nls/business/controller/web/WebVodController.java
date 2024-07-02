package com.heshi.nls.business.controller.web;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.aliyuncs.vod.model.v20170321.SearchMediaResponse;
import com.heshi.nls.business.req.GetUploadAuthReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.resp.GetUploadAuthResp;
import com.heshi.nls.business.util.VodUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController("webVodController")
@RequestMapping("/web/vod")
public class WebVodController {

    private static final Logger LOG = LoggerFactory.getLogger(WebVodController.class);

    @PostMapping("/get-upload-auth")
    public CommonResp<Object> getUploadAuth(@Valid @RequestBody GetUploadAuthReq req) throws Exception {
        LOG.info("获取上传凭证开始: ");
        CommonResp<Object> commonResp = new CommonResp<>();
        DefaultAcsClient client = VodUtil.initVodClient();
        String title = req.getKey() + "-" + req.getName();
        SearchMediaResponse searchMediaResponse = VodUtil.searchByTitle(title);
        if (searchMediaResponse.getTotal() > 0) {
            LOG.info("该文件已上传过 = {}", title);
            SearchMediaResponse.Media media = searchMediaResponse.getMediaList().get(0);
            String vid = media.getMediaId();
            GetMezzanineInfoResponse getMezzanineInfoResponse = VodUtil.getMezzanineInfo(vid);
            String fileUrl = getMezzanineInfoResponse.getMezzanine().getFileURL();
            // 直接返回原始地址，不带过期时间等参数
            fileUrl = fileUrl.split("\\?")[0];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileUrl", fileUrl);
            jsonObject.put("videoId", vid);
            commonResp.setContent(jsonObject);
        } else {
            try {
                CreateUploadVideoResponse videoResponse = VodUtil.createUploadVideo(client, title);
                GetUploadAuthResp authResp = new GetUploadAuthResp();
                authResp.setUploadAuth(videoResponse.getUploadAuth());
                authResp.setUploadAddress(videoResponse.getUploadAddress());
                authResp.setVideoId(videoResponse.getVideoId());
                LOG.info("授权码 = {}", videoResponse.getUploadAuth());
                LOG.info("地址 = {}", videoResponse.getUploadAddress());
                LOG.info("videoId = {}", videoResponse.getVideoId());
                commonResp.setContent(authResp);
            } catch (Exception e) {
                LOG.error("获取上传凭证错误", e);
            }
        }
        LOG.info("获取上传凭证结束");
        return commonResp;
    }
}
