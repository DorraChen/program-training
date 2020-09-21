package com.example.function.firstquestion.v2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author clz
 * @data 2020/9/18 16:25
 * @description 优化后
 */
public class TheCompanyProcessOptimize {

    /**
     * 将字母数大于1的单词首字母大写, 用逗号连接
     *
     * @param listOfNames
     * @return
     */
    public static String cleanNames(List<String> listOfNames) {
        return listOfNames.stream()
                .filter(l -> l != null && l.length() > 1)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(","));
    }

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> listOfNames = Arrays.asList("this", "is", "the", "first", "question");
        System.out.println(cleanNames(listOfNames));
        List<String> listOfNames1 = Arrays.asList("what", "a", "beautiful", "day");
        System.out.println(cleanNames(listOfNames1));
        List<String> listOfNames2 = Arrays.asList("a", "b", "c", "d");
        System.out.println(cleanNames(listOfNames2));
    }
}
