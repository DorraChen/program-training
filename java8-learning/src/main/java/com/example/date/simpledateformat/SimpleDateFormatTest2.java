package com.example.date.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dorra
 * @date 2022/1/7 10:56
 * @description 定义的 static 的 SimpleDateFormat 可能会出现线程安全问题
 */
public class SimpleDateFormatTest2 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            // 提交20个并发解析时间的任务线程,模拟并发环境
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(sdf.parse("2020-01-01 11:12:13"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

        /*
        运行结果有大量报错信息,且有些结果输出也不对.

        原因:
        分析 SimpleDateFormat 源码:
        SimpleDateFormat 继承了 DateFormat,DateFormat 有一个字段 Calendar;
        SimpleDateFormat 的 parse 方法调用 CalendarBuilder 的 establish 方法,来构建 Calendar;
        establish 方法内部先清空 Calendar 再构建 Calendar,整个操作没有加锁.

        如果多线程池调用 parse 方法,也就意味着多线程在并发操作一个 Calendar,
        可能会产生一个线程还没来得及处理 Calendar 就被另一个线程清空了的情况.
        format 方法也类似.因此只能在同一个线程复用 SimpleDateFormat,
        比较好的解决方式是通过 ThreadLocal 来存放 SimpleDateFormat:
        private static ThreadLocal<SimpleDateFormat> threadSafeSimpleDateFormat = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        下面是改进后的代码见:
        SimpleDateFormatTest3.java

         */


    }
}
