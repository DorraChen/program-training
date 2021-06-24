package com.example.reflection;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dorra
 * @date 2021/5/19 15:55
 * @description
 */
@Setter
@Getter
public class SubModel extends FatherModel {
    public int  age = 10;
    private String sex = "girl";
    private String favorite = "fruit";
}
