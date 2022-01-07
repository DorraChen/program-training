package com.example.date.simpledateformat;

import java.text.SimpleDateFormat;

/**
 * @author dorra
 * @date 2022/1/7 13:53
 * @description SimpleDateFormat局部变量
 */
public class SimpleDateFormatThreadLocal implements AutoCloseable {
    private SimpleDateFormatThreadLocal() {
        throw new IllegalStateException("Utility class");
    }

    private static final ThreadLocal<SimpleDateFormat> THREAD_SAFE_SIMPLE_DATE_FORMAT = new ThreadLocal<>();

    public SimpleDateFormatThreadLocal(SimpleDateFormat simpleDateFormat) {
        THREAD_SAFE_SIMPLE_DATE_FORMAT.set(simpleDateFormat);
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return THREAD_SAFE_SIMPLE_DATE_FORMAT.get();
    }


    @Override
    public void close() {
        THREAD_SAFE_SIMPLE_DATE_FORMAT.remove();
    }
}
