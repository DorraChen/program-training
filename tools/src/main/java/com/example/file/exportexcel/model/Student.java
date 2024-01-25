package com.example.file.exportexcel.model;

import lombok.Data;

/**
 * @author dorra
 * @date 2022/9/22 17:57
 * @description 学生
 */
@Data
public class Student {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
