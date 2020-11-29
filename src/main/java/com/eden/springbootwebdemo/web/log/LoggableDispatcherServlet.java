//package com.eden.springbootwebdemo.web.log;
//
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * This is Description
// *
// * @author Eden
// * @date 2020/07/20
// */
//@Component(value = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//public class LoggableDispatcherServlet extends DispatcherServlet {
//
//    @Override
//    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//        ObjectNode rootNode = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
//        try {
//            super.doDispatch(requestWrapper, responseWrapper);
//        } finally {
//            HttpLoggingUtil.updateByHttpServletResponse(rootNode, requestWrapper, responseWrapper);
//            logger.info(rootNode.toString());
//        }
//    }
//}