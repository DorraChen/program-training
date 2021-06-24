package com.example;

/**
 * @author Dorra
 * @date 2021/6/24 17:49
 * @description
 */
public class TestThreadSynchronized2 {
    public static void main(String[] args) {
        Toilet2 toilet = new Toilet2();
        //定义一起上厕所的人数
        int allPersonNumber = 20;
        for (int i = 0; i < allPersonNumber; i++) {
            //开启20个线程任务同时访问五个厕所
            new Thread(toilet).start();
        }
    }
}
