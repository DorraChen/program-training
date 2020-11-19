package com.example.stringtest;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author clz
 * @data 2020/10/12 22:46
 * @description
 */
public class SpringTest {
    @Test
    public void test() {
        BigDecimal number1 = new BigDecimal("12.12");
        BigDecimal number2 = new BigDecimal("120.10");
        System.out.println(number1);
        System.out.println(number2.stripTrailingZeros().toPlainString());
    }
}
