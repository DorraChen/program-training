package com.example.geektime;

import com.example.geektime.stack.ArrayStack;
import org.junit.Test;

/**
 * @author clz
 * @date 2021/2/1 16:39
 * @description
 */
public class GeekTimeTest {
    @Test
    public void testArrayStack() {
        ArrayStack stack = new ArrayStack(3);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
