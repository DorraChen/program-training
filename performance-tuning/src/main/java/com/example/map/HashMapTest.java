package com.example.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author clz
 * @date 2021/3/23 16:02
 * @description
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> keySetMap = new HashMap<>();
        HashMap<Integer, String> entrySetMap = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            keySetMap.put(i, "keySet");
        }
        for (int i = 0; i < 1000; i++) {
            entrySetMap.put(i, "entrySet");
        }

        long startTimeOne = System.currentTimeMillis();
        Iterator<Integer> keySetIterator = keySetMap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            String value = keySetMap.get(key);
            System.out.println(value);
        }

        System.out.println("keyset spent times:"
                + (System.currentTimeMillis() - startTimeOne));

        long startTimeTwo = System.currentTimeMillis();

        Iterator<Map.Entry<Integer, String>> entryKeyIterator = entrySetMap
                .entrySet().iterator();
        while (entryKeyIterator.hasNext()) {
            Map.Entry<Integer, String> e = entryKeyIterator.next();
            System.out.println(e.getValue());
        }
        System.out.println("entrySet spent times:"
                + (System.currentTimeMillis() - startTimeTwo));

    }
}
