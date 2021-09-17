package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author dorra
 * @date 2021/8/20 13:53
 * @description
 */
@SpringBootApplication
@PropertySource(value = {"classpath:conf.properties"})
@EnableAsync
public class ConcurrentTourApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcurrentTourApplication.class, args);
    }
}
