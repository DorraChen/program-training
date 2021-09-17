package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author dorra
 * @date 2021/8/30 12:39
 * @description
 */
@Component
public class CommonAsync {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonAsync.class);

    @Async
    public void async() {
        LOGGER.info("===============此处为异步方法开始============");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("fail:", e);
        }
        LOGGER.info("===============此处为异步方法结束============");
    }
}
