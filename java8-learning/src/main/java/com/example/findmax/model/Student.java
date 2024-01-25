package com.example.findmax.model;

import lombok.Data;

/**
 * @author dorra
 * @date 2023/5/19 10:29
 * @description
 */
@Data
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
