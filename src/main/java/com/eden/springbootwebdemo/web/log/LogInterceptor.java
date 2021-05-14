package com.eden.springbootwebdemo.web.log;

import com.alibaba.fastjson.JSON;
import com.eden.springbootwebdemo.web.RequestLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Eden
 * @date : 2021/5/15
 */
@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        startTimeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
        requestLogInfo.setCosTimeMillis(startTimeThreadLocal.get());
        HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, requestWrapper, responseWrapper);
        log.info(JSON.toJSONString(requestLogInfo));
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        startTimeThreadLocal.remove();
    }
}
