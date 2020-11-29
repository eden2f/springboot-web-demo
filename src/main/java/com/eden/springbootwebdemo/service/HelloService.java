package com.eden.springbootwebdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Eden
 * @date 2020/07/25
 */
@Service
@Slf4j
public class HelloService {

    public void test(){
        log.info("测试一下");
    }

    @Async("threadPoolTaskExecutor")
    public void testThreadPoolTaskExecutor(){
        log.info("Async 测试一下");
    }
}
