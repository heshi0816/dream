package com.dream.user.config;

import com.dream.user.util.MD5Util;
import com.dream.user.util.MyAccessDecisionManager;
import com.dream.user.util.MyFilterInvocationSecurityMetadataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                //配置认证
                        formLogin()

                //登录页面路径配置
                .loginPage("/login")
                //登录处理路径配置
                .loginProcessingUrl("/doLogin")
                //登录成功页面配置
                .defaultSuccessUrl("/index")
                //登录失败页面配置
                .failureUrl("/loginError")
                //配置以上路径，可以在非登录下访问
                .permitAll()
                //连接符
                .and()
                //配置注销
                .logout().permitAll()
                .and().
                //配置授权
                        authorizeRequests()
                //放行静态资源
                .requestMatchers("/default","/bootstrap/**", "/js/**","/map/**").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                //所有请求
                .anyRequest()
                //需要经过验证
                .authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/roleError")
                //允许客户端iframe访问
                .and().headers().frameOptions().sameOrigin()
                //取消阻止跨站请求伪造，否则会造成开发的不便利性
                .and().csrf().disable();
        return http.build();
    }

    //定义加密策略，使用BCrypt加密
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
