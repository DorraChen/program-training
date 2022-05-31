package com.example.groupby.productsale;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dorra
 * @date 2022/3/29 17:44
 * @description
 */
@Slf4j
public class ProductSalesGroupByTest {
    public static void main(String[] args) {
        List<ProductSalesDTO> list = new ArrayList<>();
        list.add(new ProductSalesDTO("1234", "3301", "2022-03-28", new BigDecimal("88")));
        list.add(new ProductSalesDTO("1234", "3301", "2022-03-28", new BigDecimal("100.88")));
        list.add(new ProductSalesDTO("1234", "3301", "2022-03-29", new BigDecimal("100")));
        list.add(new ProductSalesDTO("1234", "3301", "2022-03-29", new BigDecimal("100")));
        list.add(new ProductSalesDTO("1234", "3303", "2022-03-29", new BigDecimal("100")));
        list.add(new ProductSalesDTO("2345", "3303", "2022-03-29", new BigDecimal("100")));
        list.add(new ProductSalesDTO("2345", "3303", "2022-03-29", new BigDecimal("11.11")));

        Function<ProductSalesDTO, ProductSalesDTO> groupBy = ps ->
                new ProductSalesDTO(ps.getProductId(), ps.getCityCode(), ps.getCreateTime());

        Map<ProductSalesDTO, BigDecimal> map = list.stream()
                .collect(Collectors.groupingBy(groupBy,
                        Collectors.reducing(BigDecimal.ZERO, ProductSalesDTO::getOrderArea, BigDecimal::add)));
        map.forEach((k, v) -> log.info("productId={},cityCode={},creatTime={},v={}",
                k.getProductId(), k.getCityCode(), k.getCreateTime(), v));
        log.info("==========================");
        map.forEach((k, v) -> log.info("k={},v={}", join(k.getProductId(), k.getCityCode(), k.getCreateTime()), v));

        log.info("==========================");

        Function<ProductSalesDTO, String> groupBy2 = ps -> join(ps.getProductId(), ps.getCityCode(), ps.getCreateTime());
        Map<String, BigDecimal> map2 = list.stream().collect(Collectors.groupingBy(groupBy2, Collectors.reducing(BigDecimal.ZERO, ProductSalesDTO::getOrderArea, BigDecimal::add)));
        map2.forEach((k, v) -> log.info("k={}, v={}", k, v));

    }

    private static String join(String... strings) {
        return Arrays.stream(strings).collect(Collectors.joining("#", "", ""));
    }
}
