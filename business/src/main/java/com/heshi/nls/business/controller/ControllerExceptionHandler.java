package com.heshi.nls.business.controller;

import com.heshi.nls.business.exception.BusinessException;
import com.heshi.nls.business.resp.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 所有异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(Exception e) {
        CommonResp<Object> commonResp = new CommonResp<>();
        log.error("系统异常：", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<Object> exceptionHandler(BusinessException e) {
        CommonResp<Object> commonResp = new CommonResp<>();
        log.error("系统异常：", e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }

}

