package com.eden.springbootwebdemo.web;

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
 * This is Description
 *
 * @author Eden
 * @date 2020/07/19
 */
@Component
@Slf4j
@WebFilter(filterName = "test", urlPatterns = "/*")
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        ObjectNode rootNode = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
        chain.doFilter(requestWrapper, responseWrapper);
        HttpLoggingUtil.updateByHttpServletResponse(rootNode, requestWrapper, responseWrapper);
        log.info(rootNode.toString());
    }

    @Override
    public void destroy() {

    }
}

