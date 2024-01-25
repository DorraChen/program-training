package com.example.file.exportexcel.model;

import lombok.Data;

import java.util.List;

/**
 * @author dorra
 * @date 2022/9/29 11:28
 * @description 年级
 */
@Data
public class Grades {
    /**
     * 年级名称
     */
    private String name;
    /**
     * 班级集合
     */
    private List<Classes> classes;

    public Grades(String name, List<Classes> classes) {
        this.name = name;
        this.classes = classes;
    }
}
