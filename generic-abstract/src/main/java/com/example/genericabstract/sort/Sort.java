package com.example.genericabstract.sort;

/**
 * @author clz
 * @data 2020/7/7 9:50
 * @description 编程训练: 通用的与类型无关的泛型(类型抽象)
 */
public class Sort<T> {
    /**
     * 排序: 支持Integer, String, BigDecimal等类型的排序
     * @param value
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> T[] sort(T... value) {
        for (int i = 0; i < value.length - 1; i++) {
            for (int j = 0; j < value.length - 1 - i; j++) {
                if (value[j].compareTo(value[j + 1]) > 0) {
                    T temp = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = temp;
                }
            }
        }
        return value;
    }
}
