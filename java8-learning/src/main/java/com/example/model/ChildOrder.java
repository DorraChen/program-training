package com.example.model;

import lombok.Data;

/**
 * @author Dorra
 * @date 2021/4/13 14:50
 * @description
 */
@Data
public class ChildOrder {
    private String childOrderId;
    /**
     * 商品id
     */
    private String productId;
}
