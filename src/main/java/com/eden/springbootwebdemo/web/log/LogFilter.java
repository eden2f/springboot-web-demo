package com.eden.springbootwebdemo.web.log;

import com.alibaba.fastjson.JSON;
import com.eden.springbootwebdemo.web.RequestLogInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局日志打印过滤器
 *
 * @author Eden
 * @date 2020/07/19
 */
@Slf4j
@Component
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
        chain.doFilter(requestWrapper, responseWrapper);
        HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, requestWrapper, responseWrapper);
        log.info(JSON.toJSONString(requestLogInfo));
    }

    @Override
    public void destroy() {

    }
}

