package com.example.sort;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dorra
 * @date 2022/3/25 10:05
 * @description
 */
public class Sort {
    public static void main(String[] args) {
        List<BigDecimal> list = Arrays.asList(null, BigDecimal.valueOf(0), BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        List<BigDecimal> list1 = sortBigDecimal(list, false);
        list1.forEach(System.out::println);

        Model model = new Model("a", "1.0");
        Model model1 = new Model("b", "1.0");
        Model model2 = new Model("c", "0.5");
        Model model3 = new Model("d", null);
        Model model4 = new Model("e", null);
        Model model5 = new Model("f", "0.6");
        Model model6 = new Model("g", "0.8");
        Model model7 = new Model("h", "-0.9");
        Model model8 = new Model("i", "-0.7");
        Model model9 = new Model("j", "-0.9");
        List<Model> list2 = Arrays.asList(model, model1, model2, model3, model4, model5, model6, model7, model8, model9);
        List<Model> list3 = sortString(list2, false);
        list3.forEach(System.out::println);
    }


    private static List<Model> sortString(List<Model> list, boolean isReverseOrder) {
        if (isReverseOrder) {
            // 字符串倒序排列,为空的排在后面
            return list.stream().sorted(Comparator.comparing(t -> t.getPrice() == null
                                    ? null : new BigDecimal(t.getPrice()),
                            Comparator.nullsLast(BigDecimal::compareTo)))
                    .collect(Collectors.toList());
        }
        return list.stream().sorted(Comparator.comparing(Model::getPrice,
                        Comparator.nullsFirst(String::compareTo)).reversed())
                .collect(Collectors.toList());
    }

    /**
     * 排序
     *
     * @param list           待排序的数组
     * @param isReverseOrder 是否倒序
     * @return
     */
    private static List<BigDecimal> sortBigDecimal(List<BigDecimal> list, boolean isReverseOrder) {
        if (isReverseOrder) {
            return list.stream()
                    .sorted((a, b) -> Optional.ofNullable(b).orElse(BigDecimal.ZERO)
                            .compareTo(Optional.ofNullable(a).orElse(BigDecimal.ZERO)))
                    .collect(Collectors.toList());
        }
        return list.stream()
                .sorted(Comparator.comparing(a
                        -> Optional.ofNullable(a).orElse(BigDecimal.ZERO)))
                .collect(Collectors.toList());
    }

}
