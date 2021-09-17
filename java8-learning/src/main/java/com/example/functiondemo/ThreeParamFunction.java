package com.example.functiondemo;

/**
 * @author dorra
 * @date 2021/9/16 15:07
 * @description 自定义Function-三个参数
 */
@FunctionalInterface
public interface ThreeParamFunction<One, Two, Three, Four> {
    /**
     * Applies this function to the given arguments.
     *
     * @param one   the first function argument
     * @param two   the second function argument
     * @param three the third function argument
     * @return the function result
     */
    public Four apply(One one, Two two, Three three);
}
