package com.example.file.exportexcel.model;

import lombok.Data;

import java.util.List;

/**
 * @author dorra
 * @date 2022/9/22 17:54
 * @description 班级
 */
@Data
public class Classes {
    /**
     * 班级名称
     */
    private String name;
    private List<String> columnNameList;
    /**
     * 学生集合
     */
    private List<Student> students;
    /**
     * 等级
     */
    private String rank;

    public Classes() {
    }

    public Classes(String name, List<Student> students, String rank) {
        this.name = name;
        this.students = students;
        this.rank = rank;
    }
}
