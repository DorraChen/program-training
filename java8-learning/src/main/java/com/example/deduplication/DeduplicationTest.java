package com.example.deduplication;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dorra
 * @date 2022/6/16 16:38
 * @description 多属性去重
 */
public class DeduplicationTest {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setId("001");
        student1.setName("张三");
        student1.setSubject("语文");

        Student student2 = new Student();
        student2.setId("001");
        student2.setName("张三");
        student2.setSubject("数学");

        Student student3 = new Student();
        student3.setId("002");
        student3.setName("李四");
        student3.setSubject("语文");

        Student student4 = new Student();
        student4.setId("002");
        student4.setName("李四");
        student4.setSubject("英语");

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        // 根据id, name两个属性去重
        List<Student> uniques = students.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator
                        .comparing(o -> o.getId() + o.getName()))), ArrayList::new));
        uniques.forEach(System.out::println);

    }
}
