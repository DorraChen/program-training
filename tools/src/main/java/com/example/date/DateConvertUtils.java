package com.example.date;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Dorra
 * @date 2021/3/15 14:27
 * @description 时间转换工具类
 */
public class DateConvertUtils {
    /**
     * 年月日格式
     */
    public static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";
    /**
     * 年月日格式
     */
    public static final String YEAR_TO_MINUTE_FORMAT = "yyyy/MM/dd HH:mm";
    public static final String SHORT_YEAR_TO_DAY_FORMAT = "yy/MM/dd";
    public static final String SHORT_YEAR_TO_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时分格式
     */
    public static final String HOUR_MINUTE_FORMAT = "HH:mm";
    /**
     * 昨天
     */
    public static final String YESTERDAY = "昨天";
    /**
     * 星期几的中文
     */
    public static final String[] WEEKS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 空格
     */
    public static final String SPACE_STRING = " ";

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     */
    public static long dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SHORT_YEAR_TO_SECOND_FORMAT);
        try {
            Date date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            System.out.println("时间转换错误");
        }
        return 0;
    }

    /**
     * 处理时间戳
     * 若时间为当天，则展示为：HH:mm
     * 若时间为昨天，则展示为：昨天 HH:mm
     * 若时间为昨天以前但是在本周，则展示为：星期X HH:mm
     * 若时间为昨天以前且不是在本周，则展示为：yy/MM/dd HH:mm
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String handleDateComplex(long time) {
        //jdk的date转换为DateTime
        DateTime dt = new DateTime(time);
        //转换为jdk的date
        Date date = dt.toDate();

        long day = 2;
        try {
            day = oldDayAwayFromNow(date);
        } catch (ParseException e) {
            System.out.println("时间转换错误");
        }

        if (day < 1) {
            //今天
            SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            return format.format(date);
        } else if (day == 1) {
            //昨天
            SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            return strSplice(YESTERDAY, SPACE_STRING, format.format(date));
        } else if (isThisWeek(date.getTime())) {
            //昨天以前但是在本周
            SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            return strSplice(getWeekDay(date), SPACE_STRING, format.format(date));
        } else {
            SimpleDateFormat format = new SimpleDateFormat(YEAR_TO_MINUTE_FORMAT);
            return format.format(date);
        }
    }


    /**
     * 处理时间戳
     * 若时间为当天，则展示为：HH:mm
     * 若时间为昨天，则展示为：昨天
     * 若时间为昨天以前但是在本周，则展示为：星期X
     * 若时间为昨天以前且不是在本周，则展示为：yy/MM/dd
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String handleDateSimple(long time) {
        //jdk的date转换为DateTime
        DateTime dt = new DateTime(time);
        //转换为jdk的date
        Date date = dt.toDate();

        long day = 2;
        try {
            day = oldDayAwayFromNow(date);
        } catch (ParseException e) {
            System.out.println("时间转换错误");
        }

        if (day < 1) {
            //今天
            SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
            return format.format(date);
        } else if (day == 1) {
            //昨天
            return YESTERDAY;
        } else if (isThisWeek(date.getTime())) {
            //昨天以前但是在本周
            return getWeekDay(date);
        } else {
            SimpleDateFormat format = new SimpleDateFormat(SHORT_YEAR_TO_DAY_FORMAT);
            return format.format(date);
        }
    }


    /**
     * 输入时间距离当前时间的天数
     *
     * @param date
     * @return
     * @throws ParseException
     */
    private static long oldDayAwayFromNow(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT);
        Date old = sdf.parse(sdf.format(date));
        Date now = sdf.parse(sdf.format(new Date()));
        long oldTime = old.getTime();
        long nowTime = now.getTime();
        return (nowTime - oldTime) / (24 * 60 * 60 * 1000);
    }

    /**
     * 判断选择的日期是否是本周
     *
     * @param time
     * @return
     */
    private static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        // 2是西方的星期一, 设置一周的第一天为星期一
        calendar.setFirstDayOfWeek(2);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }

    /**
     * 获取中文星期几
     *
     * @param date
     * @return
     */
    private static String getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekIndex < 0) {
            weekIndex = 0;
        }
        return WEEKS[weekIndex];
    }

    /**
     * 字符串拼接
     *
     * @param start   开始
     * @param strings 需要拼接字的符串
     * @return
     */
    private static String strSplice(String start, String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(start);
        for (String str : strings) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
