package com.example.function.secondquestion.v3;

import java.util.function.Function;

/**
 * @author clz
 * @data 2020/9/21 09:29
 * @description 利用函数式编程, 将多个参数优化成单个参数
 */
public class AddMultiParams {
    public static Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>>
            compose = x -> y -> z -> x.apply(y.apply(z));

    public static Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
    public static Function<Function<Integer, Integer>,
            Function<Integer, Function<Integer, Integer>>> addParams = ;

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
    }

}
