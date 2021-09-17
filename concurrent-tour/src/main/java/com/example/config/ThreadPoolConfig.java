package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dorra
 * @date 2021/8/30 11:04
 * @description 线程池配置
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 线程池核心线程数
     */
    @Value("${taskExecutor.corePoolSize}")
    private int corePoolSize;
    /**
     * 允许的空闲时间
     */
    @Value("${taskExecutor.keepAliveSeconds}")
    private int keepAliveSeconds;
    /**
     * 线程池最大线程数
     */
    @Value("${taskExecutor.maxPoolSize}")
    private int maxPoolSize;
    /**
     * 缓存队列数
     */
    @Value("${taskExecutor.queueCapacity}")
    private int queueCapacity;
    /**
     * 优雅的停机策略配置:线程池关闭的时候等待所有任务都完成
     */
    @Value("${taskExecutor.waitForTasksToCompleteOnShutdown}")
    private boolean waitForTasksToCompleteOnShutdown;
    /**
     * 优雅的停机策略配置:设置等待时间
     */
    @Value("${taskExecutor.awaitTerminationSeconds}")
    private int awaitTerminationSeconds;
    /**
     * 线程名称前缀
     */
    @Value("${taskExecutor.threadNamePrefix}")
    private String threadNamePrefix;

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池核心线程数
        taskExecutor.setCorePoolSize(corePoolSize);
        // 允许的空闲时间
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池最大线程数
        taskExecutor.setMaxPoolSize(maxPoolSize);
        // 缓存队列数
        taskExecutor.setQueueCapacity(queueCapacity);
        // 优雅的停机策略配置,线程池关闭的时候等待所有任务都完成
        taskExecutor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        // 优雅的停机策略配置:设置等待时间
        taskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 拒绝策略:不在新线程中执行任务，而是有调用者所在的线程来执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }
}
