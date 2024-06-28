package com.heshi .nls.business.controller.web;

import cn.hutool.crypto.digest.DigestUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.heshi.nls.business.enums.SmsCodeUseEnum;
import com.heshi.nls.business.req.MemberRegisterReq;
import com.heshi.nls.business.resp.CommonResp;
import com.heshi.nls.business.service.MemberService;
import com.heshi.nls.business.service.SmsCodeService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/web/kaptcha")
public class WebKaptchaController {

    @Qualifier("getDefaultKaptcha")
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @GetMapping("/image-code/{imageCodeToken}")
    public void imageCode(@PathVariable(value = "imageCodeToken") String imageCodeToken, HttpServletResponse httpServletResponse) throws Exception{
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生成验证码字符串
            String createText = defaultKaptcha.createText();

            // 将生成的验证码放入redis缓存中，后续验证的时候用到
            stringRedisTemplate.opsForValue().set(imageCodeToken, createText, 300, TimeUnit.SECONDS);

            // 使用验证码字符串生成验证码图片
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @Slf4j
    @RestController
    @RequestMapping("/web/member")
    public static class WebMemberController {

        @Resource
        private MemberService memberService;

        @Resource
        private SmsCodeService smsCodeService;

        @PostMapping("/register")
        public CommonResp<Object> register(@Valid @RequestBody MemberRegisterReq req) {
            req.setPassword(DigestUtil.md5Hex(req.getPassword()));

            log.info("会员注册开始：{}", req.getMobile());

            smsCodeService.validCode(req.getMobile(), SmsCodeUseEnum.REGISTER.getCode(), req.getCode());
            log.info("注册验证码校验通过：{}", req.getMobile());

            memberService.register(req);
            return new CommonResp<>();
        }
    }
}
