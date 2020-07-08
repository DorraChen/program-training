package com.example.genericabstract.sort;

import java.util.*;

/**
 * @author clz
 * @data 2020/7/7 17:37
 * @description ------------------------
 * |  1 | 2  |  3 |  4 |
 * |  6 |  7 |  8 |  9 |
 * | 11 | 12 | 13 | 14 |
 * ---------------------
 * <p>
 * key值对:
 * "1;6;11"  "1;6;12"  "1;6;13"  "1;6;14"  "1;7;11"  "1;7;12"  ...
 * "2;6;11"  "2;6;12"  "2;6;13"  "2;6;14"  "2;7;11"  "2;7;12"  ...
 * <p>
 * 现在key的顺序可能是乱的, 需要排序, 按照上面这个规律
 * 思路: 将每个字符串按";"分割之后组成一个新的数字, 比如"1;6;11"变成"1611"
 * 这样String列表变成了数字列表, 数字的顺序就是key值对的顺序
 */
public class StringSortTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList(Arrays.asList("2;6;11", "2;6;12", "1;6;14",
                "1;7;11", "1;6;11", "1;6;12", "1;6;13", "1;7;12", "2;6;13",
                "2;6;14", "2;7;11", "2;7;12", "3;6;11", "3;6;12", "3;5;13"));
        convertToNumbers(list).forEach(x -> {
            System.out.println(x);
        });
    }

    private static List<String> convertToNumbers(List<String> list) {
        Map<String, String> map = new HashMap<String, String>(16);
        List<String> converts = new ArrayList<>();
        list.forEach(x -> {
            String convert = x.replace(";","");
            converts.add(convert);
            map.put(convert, x);
        });
        List<String> result = new ArrayList<>();
        String[] s = converts.stream().toArray(String[]::new);
        String[] strings = new Sort<String>().sort(s);
        Arrays.asList(strings).forEach(x -> {
            result.add(map.get(x));
        });
        return result;
    }
}
