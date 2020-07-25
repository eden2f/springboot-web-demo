package com.eden.springbootwebdemo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 该注解定义全局异常处理类
 *
 * @author Eden
 * @date 2020/07/19
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public RetResult<String> defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.info("服务异常, e = " + e, e);
        return RetResult.fail(e.getMessage());
    }
}
