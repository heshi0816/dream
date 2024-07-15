package com.heshi.nls.business.config;

import com.heshi.nls.business.interceptor.AdminLoginInterceptor;
import com.heshi.nls.business.interceptor.LoginInterceptor;
import com.heshi.nls.business.interceptor.WebLoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;


    @Resource
    private WebLoginInterceptor webLoginInterceptor;

    @Resource
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
        // 路径不要包含context-path
        registry.addInterceptor(webLoginInterceptor)
                .addPathPatterns("/web/**")
                .excludePathPatterns(
                        "/web/kaptcha/image-code/*",
                        "/web/member/login",
                        "/web/member/register",
                        "/web/sms-code/send-for-register",
                        "/web/sms-code/send-for-reset",
                        "/web/member/reset",
                        "/web/vod/get-upload-auth"

                );
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/kaptcha/image-code/*",
                        "/admin/user/login",
                        "/admin/report/**"
                );
    }
}
