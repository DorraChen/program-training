package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;

/**
 * @author dorra
 * @date 2021/8/30 14:04
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConcurrentTourApplication.class)
public class TaskExecutorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutorTest.class);
    @Autowired
    private Executor taskExecutor;
    @Autowired
    private CommonAsync commonAsync;

    @Test
    public void taskExecutorTest() {
        LOGGER.info("线程池设置");
        LOGGER.info("开始");
        taskExecutor.execute(() -> {
            LOGGER.info("线程池1");
            LOGGER.info("线程池1开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("fail:", e);
            }
            LOGGER.info("线程池1结束");
        });
        taskExecutor.execute(() -> {
            LOGGER.info("线程池2");
            LOGGER.info("线程池2开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("fail:", e);
            }
            LOGGER.info("线程池2结束");
        });
        LOGGER.info("结束");
    }

    @Test
    public void asyncTest() {
        LOGGER.info("异步调用测试");
        LOGGER.info("异步调用开始!");
        commonAsync.async();
        LOGGER.info("异步调用结束!");
    }
}
