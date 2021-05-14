package com.eden.springbootwebdemo.web.log;

import com.eden.springbootwebdemo.web.RequestLogInfo;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * This is Description
 *
 * @author Eden
 * @date 2020/07/25
 */
public class HttpLoggingUtil {


    public static RequestLogInfo initByHttpServletRequest(ContentCachingRequestWrapper requestWrapper) {
        RequestLogInfo requestLogInfo = new RequestLogInfo();
        requestLogInfo.setCosTimeMillis(System.currentTimeMillis());
        requestLogInfo.setRequestUri(requestWrapper.getRequestURI());
        requestLogInfo.setRemoteAddr(requestWrapper.getRemoteAddr());
        requestLogInfo.setRequestHeaders(getRequestHeaders(requestWrapper));
        return requestLogInfo;
    }

    public static void updateByHttpServletResponse(RequestLogInfo requestLogInfo, ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {
        String method = requestWrapper.getMethod();
        requestLogInfo.setMethod(method);
        if (method.equals(RequestMethod.GET.name())) {
            requestLogInfo.setRequest(requestWrapper.getParameterMap());
        } else {
            requestLogInfo.setResponse(new String(requestWrapper.getContentAsByteArray()));
        }
        requestLogInfo.setStatus(responseWrapper.getStatus());
        requestLogInfo.setResponse(new String(responseWrapper.getContentAsByteArray()));
        responseWrapper.copyBodyToResponse();
        requestLogInfo.setResponseHeaders(getResponsetHeaders(responseWrapper));
        requestLogInfo.setCosTimeMillis(System.currentTimeMillis() - requestLogInfo.getCosTimeMillis());
    }

    private static Map<String, Object> getResponsetHeaders(ContentCachingResponseWrapper response) {
        Map<String, Object> headers = new HashMap<>(16);
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            headers.put(headerName, response.getHeader(headerName));
        }
        return headers;
    }

    private static Map<String, Object> getRequestHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>(16);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
}
