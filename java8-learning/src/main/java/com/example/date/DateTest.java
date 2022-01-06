package com.example.date;

import java.util.Date;

/**
 * @author dorra
 * @date 2022/1/6 14:26
 * @description 时间测试
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date(2019, 12, 31, 11, 12, 13);
        System.out.println(date);
        // 输出 Sat Jan 31 11:12:13 CST 3920

        /*
        想要初始化一个 2019年12月31日11时12分13秒 的时间,如果执行上述代码,
        输出的结果为: Sat Jan 31 11:12:13 CST 3920 .(即 3920年1月31日11时12分13秒 )
        原因: 年应该是和 1900 的差值,月应该是从 0 到 11 而不是 1 到 12.
         */

        Date date1 = new Date(119, 11, 31, 11, 12, 13);
        System.out.println(date1);
        // 输出 Tue Dec 31 11:12:13 CST 2019
    }
}
