package com.example.date;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author dorra
 * @date 2022/1/6 17:54
 * @description TODO
 */
public class CalendarTest {
    public static void main(String[] args) {
        /*
        国际化
         */
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 11, 31, 11, 12, 13);
        System.out.println(calendar.getTime());
        // 输出 Tue Dec 31 11:12:13 CST 2019

        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        calendar1.set(2019, Calendar.DECEMBER, 31, 11, 12, 13);
        System.out.println(calendar1.getTime());
        // 输出 Wed Jan 01 00:12:13 CST 2020

    }
}
