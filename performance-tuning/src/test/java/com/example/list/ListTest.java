package com.example.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author Dorra
 * @data 2020/10/13 15:02
 * @description
 */
public class ListTest {
    /**
     * 测试ArrayList和LinkedList性能
     */
    @Test
    public void testArrayAndLinkedList() {
        ArrayListTest.addFromHeaderTest(100000);
        LinkedListTest.addFromHeaderTest(100000);


        ArrayListTest.addFromMidTest(10000);
        LinkedListTest.addFromMidTest(10000);

        ArrayListTest.addFromTailTest(1000000);
        LinkedListTest.addFromTailTest(1000000);

        ArrayListTest.deleteFromHeaderTest(100000);
        LinkedListTest.deleteFromHeaderTest(100000);

        ArrayListTest.deleteFromMidTest(100000);
        LinkedListTest.deleteFromMidTest(100000);

        ArrayListTest.deleteFromTailTest(1000000);
        LinkedListTest.deleteFromTailTest(1000000);

        ArrayListTest.getByForTest(10000);
        LinkedListTest.getByForTest(10000);

        ArrayListTest.getByIteratorTest(100000);
        LinkedListTest.getByIteratorTest(100000);
    }

    /**
     * ArrayList线程不安全
     * @see ListTest#testCopyOnWriteArrayList()
     */
    @Test
    public void testArrayList() {
        // 使用一个容器装载 100 个数字，通过 Stream 并行处理的方式将容器中为单数的数字转移到容器 parallelList
        List<Integer> integerList = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }

        List<Integer> parallelList = new ArrayList<Integer>();
        integerList.stream()
                .parallel()
                .filter(i -> i % 2 == 1)
                .forEach(i -> parallelList.add(i));
        // 可能会出现少数字、无序以及异常情况, 因为ArrayList线程不安全
        System.out.println(parallelList);
    }

    /**
     * CopyOnWriteArrayList线程安全
     */
    @Test
    public void testCopyOnWriteArrayList() {
        // 使用一个容器装载 100 个数字，通过 Stream 并行处理的方式将容器中为单数的数字转移到容器 parallelList
        List<Integer> integerList = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }

        List<Integer> parallelList = new CopyOnWriteArrayList<>();
        integerList.stream()
                .parallel()
                .filter(i -> i % 2 == 1)
                .forEach(i -> parallelList.add(i));
        // 线程安全, 不会出错, 但是无序
        System.out.println("排序前" + parallelList);
        System.out.println("排序后" + parallelList.stream().sorted().collect(Collectors.toList()));
    }
}
