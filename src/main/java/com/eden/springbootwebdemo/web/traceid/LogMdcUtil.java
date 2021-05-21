package com.eden.springbootwebdemo.web.traceid;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

/**
 * @author huangminpeng
 * @date 2021/5/20 10:33
 */
@Slf4j
public class LogMdcUtil {

    private static final String LOG_TRACE_ID_TAG = "TRACE_ID";

    public static void setTraceIdOrElseIgnore() {
        try {
            Map<String, String> map = MDC.getCopyOfContextMap();
            MDC.setContextMap(map);
            String traceId = MDC.get(LOG_TRACE_ID_TAG);
            if (StringUtils.isBlank(traceId)) {
                traceId = UUID.randomUUID().toString().replaceAll("-", "");
                MDC.put(LOG_TRACE_ID_TAG, traceId);
            }
        } catch (Exception ignore) {
            log.info("设置traceId报错", ignore);
        }
    }

    public static void mdcClear() {
        MDC.clear();
    }
}
