package com.example.function.secondquestion.v2;

import java.util.function.Function;

/**
 * @author Dorra
 * @data 2020/9/21 09:15
 * @description 利用函数式编程,将两个参数优化成单个参数.
 */
public class AddOptimize {
    public static Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(add.apply(1).apply(2));
    }
}
