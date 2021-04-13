package com.example;

import com.example.date.DateConvertUtils;
import org.junit.Test;

/**
 * @author clz
 * @data 2020/10/12 22:46
 * @description
 */
public class DateConvertUtilsTest {
    @Test
    public void testConvertStr() {
        long time = DateConvertUtils.dateToStamp("2021-03-21 12:12:12");
        System.out.println(DateConvertUtils.handleDateComplex(time));
        System.out.println(DateConvertUtils.handleDateSimple(time));
    }
}
