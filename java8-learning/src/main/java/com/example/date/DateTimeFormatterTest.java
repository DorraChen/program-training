package com.example.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author dorra
 * @date 2022/1/6 17:53
 * @description TODO
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {

        /*
        全球有 24 个时区，同一个时刻不同时区（比如中国上海和美国纽约）的时间是不一样的。
        对于需要全球化的项目，如果初始化时间时没有提供时区，那就不是一个真正意义上的时间，
        只能认为是我看到的当前时间的一个表示。
         */
        System.out.println(new Date(0));
        // 输出 Thu Jan 01 08:00:00 CST 1970
        System.out.println(TimeZone.getDefault().getID() + ":" + TimeZone.getDefault().getRawOffset());
        // 输出 Asia/Shanghai:28800000


        // 一个时间表示
        String stringDate = "2020-01-02 22:00:00";
        // 初始化三个时区
        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId timeZoneNY = ZoneId.of("America/New_York");
        ZoneId timeZoneJST = ZoneOffset.ofHours(9);
        // 格式化器
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneJST);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        // 使用 DateTimeFormatter 格式化时间，可以通过 withZone 方法直接设置格式化使用的时区
        System.out.println((timeZoneSH.getId() + outputFormat.withZone(timeZoneSH).format(zonedDateTime)));
        // 输出 Asia/Shanghai2020-01-02 21:00:00 +0800
        System.out.println((timeZoneNY.getId() + outputFormat.withZone(timeZoneNY).format(zonedDateTime)));
        // 输出 America/New_York2020-01-02 08:00:00 -0500
        System.out.println((timeZoneJST.getId() + outputFormat.withZone(timeZoneJST).format(zonedDateTime)));
        // 输出 +09:002020-01-02 22:00:00 +0900
    }
}
