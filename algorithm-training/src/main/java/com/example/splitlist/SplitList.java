package com.example.splitlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dorra
 * @data 2020/9/11 11:16
 * @description 算法训练
 * 在开发过程中,遇到一个需求
 * 查出商品列表(服务返回的商品顺序是按照业务定的顺序,比如上下架时间,购买数量等)
 * 前端需要把商品分成左右模块展示,每页四个. 如图:
 * ===============第一页=============|   ===============第二页=============
 * |----  1 ----- | |----  3 ----- |   |----  5 ----- | |----  7 ----- |
 * |----  2 ----- | |----  4 ----- |   |----  6 ----- | |----  8 ----- |
 * ====================================================================
 * <p>
 * 后端返回:
 * 左边的商品顺序是第1,2, 5,6, 9,10, ...
 * 右边的商品顺序是第3,4, 7,8, 11,12, ...
 * 也就是从第一个商品开始, 取两个放左,取两个放右, 再取两个放左, 取两个放右...
 * <p>
 * ===============================================================
 * 我找的突破点是 左边取两个右边取两个依次类推
 * 那么对应原始列表的index就是
 * 第一个列表: 0,1,4,5,8,9
 * 第二个列表: 2,3,6,7,10,11
 * ===============================================================
 */
public class SplitList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        /**第一步:最简单的出发 */
        split1(list, left, right);
        /*
        执行结果:
        [1, 2, 5, 6, 9, 10, 13, 14, 17, 18]
        [3, 4, 7, 8, 11, 12, 15, 16, 19, 20]
         */

        /**第二步:想到可能需要会改, 万一要按照3个或者4个或者...来分割怎么办 */
        split2(list, left, right, 4);
        /*
        执行结果:
        [1, 2, 3, 4, 9, 10, 11, 12, 17, 18, 19, 20]
        [5, 6, 7, 8, 13, 14, 15, 16]
         */


    }

    /**
     * 第一步:最简单的出发
     *
     * @param list  原始list
     * @param left  左list
     * @param right 右list
     */
    private static void split1(List<String> list,
                               List<String> left,
                               List<String> right) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            int od = index / 2;
            if (i % 2 == 1) {
                index = index + 2;
            }
            if (od % 2 == 0) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        System.out.println(left);
        System.out.println(right);
        left.clear();
        right.clear();
    }


    /**
     * 第二步:想到可能需要会改, 万一要按照3个来分割怎么办
     *
     * @param list   原始list
     * @param left   左list
     * @param right  右list
     * @param module 分割的大小
     */
    private static void split2(List<String> list,
                               List<String> left,
                               List<String> right,
                               int module) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            int od = index / module;
            if (i % module == module - 1) {
                index = index + module;
            }
            if (od % 2 == 0) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        System.out.println(left);
        System.out.println(right);
        left.clear();
        right.clear();
    }
}
