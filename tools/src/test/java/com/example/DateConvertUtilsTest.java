package com.example;

import com.example.date.DateConvertUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dorra
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

    @Test
    public void yearTest() {
        String date = "1981-02-01 00:00:00.000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parseDate = dateFormat.parse(date);
            String yearOfBirth = dateFormat.format(parseDate);
            System.out.println(yearOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
