package com.example.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author dorra
 * @date 2022/1/6 17:52
 * @description TODO
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {
        // 关于 SimpleDateFormat
        Calendar calendar2 = Calendar.getInstance();
        // 设置时间为 2019年12月29日
        calendar2.set(2019, Calendar.DECEMBER, 29, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("格式化: " + sdf.format(calendar2.getTime()));
        System.out.println("weekYear:" + calendar2.getWeekYear());
        System.out.println("firstDayOfWeek:" + calendar2.getFirstDayOfWeek());
        System.out.println("minimalDaysInFirstWeek:" + calendar2.getMinimalDaysInFirstWeek());
        /*
        输出结果:

        格式化: 2020-12-29
        weekYear:2020
        firstDayOfWeek:1
        minimalDaysInFirstWeek:1

        SimpleDateFormat 的正确格式应该是 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         */
    }
}
