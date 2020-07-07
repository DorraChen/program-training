package com.example.genericabstract.sort;

/**
 * @author clz
 * @data 2020/7/7 10:44
 * @description Test
 */
public class SortTest {
    public static void main(String[] args) {
        Integer[] integers = new Sort<Integer>().sort(1, 4, 3, 4, 3, 2, 9);
        // 排序后结果
        for (int num : integers) {
            System.out.print(num + " ");
        }
        System.out.println();

        String[] strings = new Sort<String>().sort("hello", "ni", "hao", "a", "5666");
        // 排序后结果
        for (String s : strings) {
            System.out.print(s + " ");
        }
    }
}
