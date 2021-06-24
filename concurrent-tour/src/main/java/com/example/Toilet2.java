package com.example;

import java.util.LinkedList;

/**
 * @author Dorra
 * @date 2021/6/24 17:47
 * @description
 */
public class Toilet2 implements Runnable {
    /**
     * 定义资源个数
     */
    int value = 5;
    /**
     * “让权等待”后的阻塞线程用链表进行串联起来
     */
    LinkedList linkedList = new LinkedList();

    @Override
    public void run() {
        //当申请一个单位的该类资源时候，资源就会减少一个
        value--;
        if (value < 0) {
            //判断剩余资源是否小于零，如果小于零，进行下列代码(注意为啥小于零不包含在内，因为value--,当value=1时，后申请到一个资源后value=0，进入厕所权限已经申请到，直接上厕所不必等待 )
            try {
                //阻塞自己
                this.wait();
                //把阻塞的线程添加到链表中，此时链表的长度就是阻塞线程个数
                linkedList.add(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName() + "厕所位置一共有5个，剩余" + value + "个，请您耐心等待");
        //Person上完厕所以后要归还资源，所以value++;
        value++;
        if (value <= 0) {
            //判断如果资源小于等于零的话，那么唤醒链表中第一个排队的person来上厕所
            linkedList.removeFirst().notify();
        }


    }
}
