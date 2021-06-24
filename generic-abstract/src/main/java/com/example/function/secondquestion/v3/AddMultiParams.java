package com.example.function.secondquestion.v3;

import java.util.function.Function;

/**
 * @author Dorra
 * @data 2020/9/21 09:29
 * @description 利用函数式编程, 将多个参数优化成单个参数
 */
public class AddMultiParams {

    public static Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
    public static Function<Integer, Function<Integer, Function<Integer, Integer>>> add1
            = x -> y ->z -> add.apply(x).apply(add.apply(y).apply(z));

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(add1.apply(1).apply(2).apply(3));
    }

}
