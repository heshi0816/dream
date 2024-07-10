package com.heshi.nls.business.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.heshi.nls.business.context.LoginUserContext;
import com.heshi.nls.business.resp.UserLoginResp;
import com.heshi.nls.business.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURL().toString();
        log.info("接口登录拦截，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("token");
        log.info("控台登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            log.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            log.info("获取用户登录token：{}", token);
            JSONObject loginUser = JwtUtil.getJSONObject(token);
            log.info("当前登录用户：{}", loginUser);
            UserLoginResp user = JSONUtil.toBean(loginUser, UserLoginResp.class);
            LoginUserContext.setUser(user);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LoginUserContext.removeUser();
    }
}