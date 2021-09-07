package com.example.model;

import com.example.model.ChildOrder;
import lombok.Data;

import java.util.List;

/**
 * @author Dorra
 * @date 2021/4/13 14:49
 * @description
 */
@Data
public class ParentOrder {
    private String orderId;
    private List<ChildOrder> childOrderList;
}
