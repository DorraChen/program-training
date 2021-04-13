package com.example;

import com.example.jdk8.model.ChildOrder;
import com.example.jdk8.model.ParentOrder;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dorra
 * @date 2021/4/13 14:53
 * @description
 */
public class JDK8Test {
    @Test
    public void test() {
        // init
        ChildOrder childOrder1 = new ChildOrder() {{
            setProductId("1");
        }};
        ChildOrder childOrder2 = new ChildOrder() {{
            setProductId("2");
        }};
        ChildOrder childOrder3 = new ChildOrder() {{
            setProductId("3");
        }};
        ChildOrder childOrder4 = new ChildOrder() {{
            setProductId("4");
        }};
        ChildOrder childOrder5 = new ChildOrder() {{
            setProductId("5");
        }};
        ChildOrder childOrder6 = new ChildOrder() {{
            setProductId("6");
        }};
        ChildOrder childOrder7 = new ChildOrder() {{
            setProductId("7");
        }};
        ChildOrder childOrder8 = new ChildOrder() {{
            setProductId("8");
        }};
        List<ChildOrder> childOrderList1 = new ArrayList<>(Arrays.asList(childOrder1, childOrder2));
        List<ChildOrder> childOrderList2 = new ArrayList<>(Arrays.asList(childOrder3, childOrder4));
        List<ChildOrder> childOrderList3 = new ArrayList<>(Arrays.asList(childOrder5, childOrder6));
        List<ChildOrder> childOrderList4 = new ArrayList<>(Arrays.asList(childOrder7, childOrder8));
        ParentOrder parentOrder1 = new ParentOrder() {{
            setChildOrderList(childOrderList1);
        }};
        ParentOrder parentOrder2 = new ParentOrder() {{
            setChildOrderList(childOrderList2);
        }};
        ParentOrder parentOrder3 = new ParentOrder() {{
            setChildOrderList(childOrderList3);
        }};
        ParentOrder parentOrder4 = new ParentOrder() {{
            setChildOrderList(childOrderList4);
        }};
        List<ParentOrder> parentOrders = new ArrayList<>(Arrays.asList(parentOrder1, parentOrder2, parentOrder3, parentOrder4));

        List<ChildOrder> childOrders = parentOrders.stream().map(ParentOrder::getChildOrderList).flatMap(List::stream).collect(Collectors.toList());
        List<String> productIds = childOrders.stream().map(ChildOrder::getProductId).collect(Collectors.toList());
        System.out.println(new Gson().toJson(productIds));
    }
}
