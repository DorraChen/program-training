package com.example.date;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author dorra
 * @date 2021/8/31 10:56
 * @description 时间工具类
 */
public class TimeUtils {


    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_1 = "yyyy-MM-dd";


    /**
     * 获取某一时间 几天前/后 的时间
     *
     * @param date 时间
     * @param day  几天前/后(负数为几天前, 正数为几天后)
     * @return Date
     */
    public static Date getDateFromDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取某一时间 几天前/后 的时间
     *
     * @param date 时间
     * @param day  几天前/后(负数为几天前, 正数为几天后)
     * @return String
     */
    public static String getDateFromDayStr(Date date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(getDateFromDay(date, day));
    }

    /**
     * 获取当天的开始时间
     *
     * @return Date
     */
    public static Date getTodayBeginDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的开始时间
     *
     * @return String
     */
    public static String getTodayBeginStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(getTodayBeginDate());
    }

    /**
     * 获取当天的结束时间
     *
     * @return Date
     */
    public static Date getTodayEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return String
     */
    public static String getTodayEndStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(getTodayEndDate());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDayTime() {
        //格式化
        DateTimeFormatter fmTime = DateTimeFormatter.ofPattern(DATE_FORMAT);
        //当前时间
        LocalDateTime nowTime = LocalDateTime.now(ZoneOffset.of("+8"));
        String format = nowTime.format(fmTime);
        return format;
    }

    /**
     * 获取n天前的时间
     *
     * @param day 正数为后推，负数前推
     * @return
     */
    public static String getDayTime(int day) {
        //格式化
        DateTimeFormatter fmTime = DateTimeFormatter.ofPattern(DATE_FORMAT);
        //当前时间
        LocalDateTime nowTime = LocalDateTime.now(ZoneOffset.of("+8"))
                .plusDays(day);
        String format = nowTime.format(fmTime);
        return format;
    }

    /**
     * 获取某个时间的n小时前的时间
     *
     * @param hours
     * @return
     */
    public static String getHoursTime(String time,int hours) {
        //格式化
        DateTimeFormatter fmTime = DateTimeFormatter.ofPattern(DATE_FORMAT);
        //指定时间
        LocalDateTime cTime = LocalDateTime.parse(time, fmTime)
                .plusHours(hours);
        String format = cTime.format(fmTime);
        return format;
    }

    /**
     * 比较时间大小
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareTime(String time1, String time2) {
        //格式化
        DateTimeFormatter fmTime = DateTimeFormatter.ofPattern(DATE_FORMAT);
        // 时间解析
        LocalDateTime localDateTime1 = LocalDateTime.parse(time1, fmTime);
        LocalDateTime localDateTime2 = LocalDateTime.parse(time2, fmTime);
        // time1  是否在time2  之后  即 time1 大
        return localDateTime1.isAfter(localDateTime2);
    }

    /**
     * 剩余时间戳
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getCountdowns(String startTime, String endTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime startTimeDate = LocalDateTime.parse(startTime, df);
        LocalDateTime endTimeDate = LocalDateTime.parse(endTime, df);
        Long countdowns = Duration.between(startTimeDate, endTimeDate).toMillis();
        return countdowns;
    }

    /**
     * 返回指定月份的第一天
     *
     * @param dateStr 年月
     * @param returnFormat 返回格式
     * @return
     * @throws ParseException
     */
    public static String getFirstDayOfGivenMonth(String dateStr, String returnFormat) throws ParseException {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 0);
        returnFormat = Optional.ofNullable(returnFormat).orElse(DATE_FORMAT);
        SimpleDateFormat returnSdf = new SimpleDateFormat(returnFormat);
        return returnSdf.format(calendar.getTime());
    }

    /**
     * 返回指定月份的最后一天
     *
     * @param dateStr 年月
     * @param returnFormat 返回格式
     * @return
     * @throws ParseException
     */
    public static String getLastDayOfGivenMonth(String dateStr, String returnFormat) throws ParseException {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        returnFormat = Optional.ofNullable(returnFormat).orElse(DATE_FORMAT);
        SimpleDateFormat returnSdf = new SimpleDateFormat(returnFormat);
        return returnSdf.format(calendar.getTime());
    }



    /**
     * 获取清算周期的-周期 上周时间 YYYY-MM-DD 00:00:00
     * 今天为第一天
     * <p>
     * date为范围起点，推算上周时间
     *
     * @param date       以此时间推到开始时间
     * @param targetWeek 根据清算规则 设置本周的第几天
     * @param cycle 0表示配置的是本周结算,1表示配置的是下周结算
     * @return Date
     */
    public static Date getClearLastWeekTime(Date date, Integer cycle, Integer targetWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 本周上周
        if (cycle != 0) {
            calendar.add(Calendar.DATE, -7);
        }
        setWeekDay(calendar, targetWeek);
        return calendar.getTime();
    }

    /**
     * 设定目标日期周的 周几
     *
     * @param calendar    目标日期
     * @param executeTime 周几
     */
    public static void setWeekDay(Calendar calendar, Integer executeTime) {
        //当前是周几
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //转成中国人的星期
        int currentWeek = dayWeek == 1 ? 7 : dayWeek - 1;
        //当前日期不为目标日期，才做修改
        if (currentWeek != executeTime) {
            calendar.add(Calendar.DATE, executeTime - currentWeek);
        }
    }

    /**
     * 获取今天的本周的星期几
     *
     * @return
     */
    public static Integer getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //当前是周几
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //转成中国人的星期习惯
        int currentWeek = dayWeek == 1 ? 7 : dayWeek - 1;
        return currentWeek;
    }

    /**
     * 时间格式化成字符串
     *
     * @param time       时间字符串
     * @return
     */
    public static String timeFormatToStr(Date time) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public static void main(String[] args) {
        //星期几 1-7
        Date targetDate = new Date();
        // 0表示配置的是本周结算,1表示配置的是下周结算
        Date startTime = getClearLastWeekTime(targetDate, 1, 1);
        Date endTime = getClearLastWeekTime(targetDate, 1, 5);
        System.out.println(timeFormatToStr(startTime));
        System.out.println(timeFormatToStr(endTime));
    }
}
