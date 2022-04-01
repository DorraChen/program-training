package com.example.insert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dorra
 * @date 2022/3/23 13:34
 * @description
 */
@Slf4j
public class ListInsertList {

    public static void main(String[] args) {
        List<String> letter = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
        List<String> number = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        List<String> combine = combine(letter, number);
        log.info("combine:{}", combine);
        List<String> combineNew = combineNew(letter, number, 4, 2);
        log.info("combine:{}", combineNew);
    }

    private static List<String> combineNew(List<String> letter, List<String> number, int n, int m) {
        List<String> combine = new ArrayList<>();
        if (CollectionUtils.isEmpty(letter)) {
            return number;
        }
        if (CollectionUtils.isEmpty(number)) {
            return letter;
        }
        int total = letter.size() + number.size();
        int group = total / (n + m) + 1;
        for (int i = 0; i < group; i++) {
            int letterIndex0 = i * n;
            int letterIndex1 = (i + 1) * n;
            int numberIndex0 = i * m;
            int numberIndex1 = (i + 1) * m;
            if(letterIndex0 < letter.size()){
                if (letterIndex1 < letter.size()) {
                    combine.addAll(letter.subList(letterIndex0, letterIndex1));
                } else {
                    combine.addAll(letter.subList(letterIndex0, letter.size()));
                    if (numberIndex0 < number.size()) {
                        combine.addAll(number.subList(numberIndex0, number.size()));
                    }
                    break;
                }
            }
            if (numberIndex0 < number.size()) {
                if (numberIndex1 < number.size()) {
                    combine.addAll(number.subList(numberIndex0, numberIndex1));
                } else {
                    combine.addAll(number.subList(numberIndex0, number.size()));
                    if (letterIndex0 < letter.size()) {
                        combine.addAll(letter.subList(letterIndex0, letter.size()));
                    }
                }
            }
        }
        return combine;
    }

    /**
     * 4+1
     *
     * @param letter
     * @param number
     * @return
     */
    private static List<String> combine(List<String> letter, List<String> number) {
        if (CollectionUtils.isEmpty(letter)) {
            return number;
        }
        if (CollectionUtils.isEmpty(number)) {
            return letter;
        }
        // 深拷贝
        List<String> newLetter = new ArrayList<>(letter);
        List<String> newNumber = new ArrayList<>(number);
        for (int i = 0; i < newLetter.size(); i++) {
            i += 4;
            if (CollectionUtils.isEmpty(newNumber)) {
                break;
            }
            if (i >= newLetter.size()) {
                break;
            }
            newLetter.add(i, newNumber.get(0));
            newNumber.remove(0);
        }
        if (!CollectionUtils.isEmpty(newNumber)) {
            newLetter.addAll(newNumber);
        }
        return newLetter;
    }
}
