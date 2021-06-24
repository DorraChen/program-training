package com.example.function.thirdquestion;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Dorra
 * @data 2020/9/21 09:31
 * @description 斐波那契函数
 */
public class FibonacciFunction {
    /**
     * 斐波那契函数
     * 斐波那契函数的定义是: F[n]=F[n-1]+F[n-2] (n>=2,F[0]=0,F[1]=1)
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, ...
     *
     * @param n 第几位(从0开始)
     * @return
     */
    public static Integer fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 优化
     * 写成函数式
     */
    public static BiFunction<BiFunction, Integer, Integer> fibFunction
            = (f, n) -> (n == 0) ? 0
            : ((n == 1) ? 1 : (Integer) f.apply(f, n - 1) + (Integer) f.apply(f, n - 2));

    public static Function<Integer, Integer> function = x -> fibFunction.apply(fibFunction, x);

    /**
     * 测试一下
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(fib(13));
        System.out.println(function.apply(13));
    }
}
