package com.example.fieldresolution.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author clz
 * @data 2020/10/22 11:34
 * @description
 */
@Data
public class ModelThree {
    @JSONField(name = "username")
    private String userName;
    @JSONField(name = "userage")
    private Integer userAge;
}
