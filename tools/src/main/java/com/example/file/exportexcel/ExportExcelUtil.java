package com.example.file.exportexcel;

import com.example.file.exportexcel.model.Classes;
import com.example.file.exportexcel.model.Grades;
import com.example.file.exportexcel.model.Student;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dorra
 * @date 2022/9/29 11:20
 * @description
 */
public class ExportExcelUtil {
    private static String BLANK = "";

    private static String data = "";

    public static void main(String[] args) {
//        List<String> columnNameList = Arrays.asList("序号", "年级", "班级", "姓名", "年龄", "等级");
        List<String> columnNameList = new ArrayList<>();
        columnNameList.add("序号");
        Classes classes = new Classes();
        classes.setColumnNameList(columnNameList);
        System.out.println(new Gson().toJson(classes));
        columnNameList.add("年级");
        System.out.println(new Gson().toJson(classes));

        Student student = new Student("clz", 11);
        List<Student> students = new ArrayList<>();
        students.add(student);
        classes.setStudents(students);
        System.out.println(new Gson().toJson(classes));
        students.get(0).setAge(12);
        System.out.println(new Gson().toJson(classes));

    }

    public static String[] headToArray(List<String> autoConfigList) {
        int size = autoConfigList.size();
        // 标题
        String[] heads = new String[size];
        for (int i = 0; i < size; i++) {
            if (autoConfigList.get(i) == null) {
                heads[i] = BLANK;
            } else {
                heads[i] = autoConfigList.get(i);
            }
        }
        return heads;
    }
}
