package com.example.date.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dorra
 * @date 2022/1/7 13:50
 * @description 定义的 static 的 SimpleDateFormat 可能会出现线程安全问题--->SimpleDateFormatTest2.java 解决方案
 */
public class SimpleDateFormatTest3 {

    public static void main(String[] args) throws InterruptedException {
        try (SimpleDateFormatThreadLocal sdfThreadLocal = new SimpleDateFormatThreadLocal(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))) {
            ExecutorService threadPool1 = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 20; i++) {
                // 提交20个并发解析时间的任务线程,模拟并发环境
                threadPool1.execute(() -> {
                    for (int j = 0; j < 10; j++) {
                        try {
                            System.out.println(SimpleDateFormatThreadLocal.getSimpleDateFormat().parse("2020-01-01 11:12:13"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            threadPool1.shutdown();
            threadPool1.awaitTermination(1, TimeUnit.HOURS);

            System.out.println(sdfThreadLocal);
        }

    }
}
