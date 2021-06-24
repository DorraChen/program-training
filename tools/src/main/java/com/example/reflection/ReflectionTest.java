package com.example.reflection;

import java.lang.reflect.Field;

/**
 * @author Dorra
 * @date 2021/5/19 15:53
 * @description
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        SubModel model = new SubModel();
        Class clazz = model.getClass();
        Field[] fs = clazz.getFields();
        //获取类中public Filed包括父类
        for (Field field : fs) {
            System.out.println(field);
        }
        System.out.println("---------------------------");

        fs = clazz.getDeclaredFields();
        //获取类中所有的Filed不包括父类
        for (Field field : fs) {
            System.out.println(field);
        }
        System.out.println("---------------------------");
        Field f = clazz.getDeclaredField("sex");
        f.setAccessible(true);
        //获取指定的public Filed
        System.out.println(f.get(model));
    }
}
