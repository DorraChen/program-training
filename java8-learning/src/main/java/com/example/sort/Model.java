package com.example.sort;

import lombok.Data;

/**
 * @author dorra
 * @date 2022/3/25 11:00
 * @description
 */
@Data
public class Model {
    private String label;
    private String price;

    public Model(String label, String price) {
        this.label = label;
        this.price = price;
    }
}
