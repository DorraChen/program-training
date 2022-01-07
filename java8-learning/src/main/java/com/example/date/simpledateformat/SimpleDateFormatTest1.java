package com.example.date.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author dorra
 * @date 2022/1/6 17:52
 * @description SimpleDateFormat格式化
 */
public class SimpleDateFormatTest1 {
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

        JDK 的文档中有说明, 小写的 y 为年,大写的 Y 为 week year.
        https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat 的正确格式应该是 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         */
    }
}
