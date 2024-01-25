package com.example.findmax;

import com.example.findmax.model.Student;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author dorra
 * @date 2023/5/19 10:29
 * @description
 */
public class FindMax {
    private static Gson gson = new Gson();
    public static void main(String[] args) {
        Student student1 = new Student("li", 15);
        Student student2= new Student("wang", 10);
        Student student3 = new Student("chen", 15);
        Student student4 = new Student("zhao", 9);
        Student student5 = new Student("qian", 9);
//        List<Student> list = Arrays.asList(student1, student2, student3, student4, student5);
        List<Student> list = new ArrayList<>();
        Student max = list.stream().max(Comparator.comparing(Student::getAge)).get();
        System.out.println(gson.toJson(max));
    }
}
