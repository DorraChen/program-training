package com.example.function.thirdquestion;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Dorra
 * @data 2020/9/23 13:59
 * @description 利用函数式编程实现阶乘
 */
public class FactorialFunction {
    public static BiFunction<BiFunction, Integer, Integer> factorial
            = (f, v) -> (v == 0) ? 1 : v * (Integer) f.apply(f,v-1);
    public static Function<Integer, Integer> function =
            x -> factorial.apply(factorial, x);

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(function.apply(4));
    }


}
