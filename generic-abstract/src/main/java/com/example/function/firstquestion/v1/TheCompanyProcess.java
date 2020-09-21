package com.example.function.firstquestion.v1;

import java.util.Arrays;
import java.util.List;

/**
 * @author clz
 * @data 2020/9/18 16:23
 * @description 优化前
 */
public class TheCompanyProcess {
    /**
     * 将字母数大于1的单词首字母大写, 用逗号连接
     *
     * @param listOfNames
     * @return
     */
    public static String cleanNames(List<String> listOfNames) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listOfNames.size(); i++) {
            if (listOfNames.get(i).length() > 1) {
                result.append(capitalizeString(listOfNames.get(i))).append(",");
            }
        }
        return result.substring(0, result.length() - 1).toString();
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String capitalizeString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public static void main(String[] args) {
        List<String> listOfNames = Arrays.asList("this", "is", "the", "first", "question");
        System.out.println(cleanNames(listOfNames));
        List<String> listOfNames1 = Arrays.asList("what", "a", "beautiful", "day");
        System.out.println(cleanNames(listOfNames1));
        List<String> listOfNames2 = Arrays.asList("a", "b", "c", "d");
        System.out.println(cleanNames(listOfNames2));
    }
}