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

        /*Model model = new Model("a", "1.0");
        Model model1 = new Model("b", "1.0");
        Model model2 = new Model("c", "11.5");
        Model model3 = new Model("d", "0.5");
        Model model4 = new Model("e", null);
        Model model5 = new Model("f", null);
        Model model6 = new Model("g", "0.8");
        Model model7 = new Model("h", "0.9");
        Model model8 = new Model("i", "-0.9");
        Model model9 = new Model("j", "-0.7");
        Model model10 = new Model("k", "-0.9");*/
        Model model = new Model("a", "15");
        Model model1 = new Model("b", "2.15");
        Model model2 = new Model("c", "5");
        Model model3 = new Model("d", "33");
        Model model4 = new Model("e", "5");
        Model model5 = new Model("f", "3.22");
        Model model6 = new Model("g", null);
        Model model7 = new Model("h", null);
        Model model8 = new Model("i", "3.22");
        Model model9 = new Model("j", "5");
        Model model10 = new Model("k", "1.23");
        Model model11 = new Model("l", "55");
        Model model12 = new Model("m", "4.51");
        Model model13 = new Model("n", "19");
        Model model14 = new Model("o", "0");
        List<Model> list2 = Arrays.asList(model, model1, model2, model3, model4, model5,
                model6, model7, model8, model9, model10, model11, model12, model13, model14);
        List<Model> list3 = sortString(list2, true);
        list3.forEach(System.out::println);
    }


    private static List<Model> sortString(List<Model> list, boolean isReverseOrder) {
        /*new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o2.getPrice().compareTo(o1.getPrice());
            }
        };*/
        if (isReverseOrder) {
            // 字符串倒序排列,为空的排在后面
            /*return list.stream().sorted((a, b) -> getPriceBigDecimal(b.getPrice()).compareTo(getPriceBigDecimal(a.getPrice())))
                    .collect(Collectors.toList());*/
            /*return list.stream().sorted(Comparator.comparing(a -> getPriceBigDecimal(a.getPrice()),
                            Comparator.nullsLast(BigDecimal::compareTo)))
                    .collect(Collectors.toList());*/
            return list.stream().sorted(Comparator.comparing(t ->
                                    Optional.ofNullable(t.getPrice()).map(BigDecimal::new).orElse(null),
                            Comparator.nullsFirst(BigDecimal::compareTo).reversed()))
                    .collect(Collectors.toList());
        }
        return list.stream().sorted(Comparator.comparing(t ->
                                Optional.ofNullable(t.getPrice()).map(BigDecimal::new).orElse(null),
                        Comparator.nullsLast(BigDecimal::compareTo)))
                .collect(Collectors.toList());
    }

    private static BigDecimal getPriceBigDecimal(String price) {
        return price == null ? BigDecimal.ZERO : new BigDecimal(price);
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
