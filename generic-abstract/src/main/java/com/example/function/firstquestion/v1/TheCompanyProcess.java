package com.example.function.firstquestion.v1;

import java.util.Arrays;
import java.util.List;

/**
 * @author clz
 * @data 2020/9/18 16:23
 * @description
 */
public class TheCompanyProcess {
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
    }
}