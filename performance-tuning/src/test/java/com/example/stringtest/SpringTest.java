package com.example.stringtest;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Dorra
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

    @Test
    public void testStr() {
        String str= "ab" + "cd" + "ef";
    }

    @Test
    public void testAddStr() {
        String str = "abcdef";
        for(int i=0; i<1000; i++) {
            str = str + i;
        }
    }

    @Test
    public void testNewStr() {
        String str1= "abc";
        String str2= new String("abc");
        String str3= str2.intern();
        System.out.println(str1==str2);
        System.out.println(str2==str3);
        System.out.println(str1==str3);
    }
}
