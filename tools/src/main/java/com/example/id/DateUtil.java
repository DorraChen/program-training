package com.example.id;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * @author dorra
 * @date 2022/1/5 13:38
 * @description 时间工具类
 */
public class DateUtil {
    public static String PATTERN_DATETIME = "yyyyMMddHHmmssSSS";
    public static String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 返回string的时间戳
     *
     * @param mils
     * @return
     */
    public static String fomartToNumber(long mils) {
        LocalDateTime time = new Date(mils).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return formatLocalDateTime(time, PATTERN_DATETIME);
    }

    /**
     * 将localDateTime格式化
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        // 如果设置为空,择获取当前时间
        LocalDateTime currentDate = Optional.ofNullable(localDateTime).orElse(LocalDateTime.now());
        String currentPattern = Optional.ofNullable(pattern).orElse(DateUtil.PATTERN_DATE);
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(currentPattern);
        return ofPattern.format(currentDate);
    }
}
