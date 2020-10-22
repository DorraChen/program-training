package com.example.fieldresolution.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author clz
 * @data 2020/10/22 11:34
 * @description
 */
@Data
public class ModelTwo {
    @SerializedName(value = "username", alternate = "userName")
    private String userName;
    @SerializedName(value = "userage", alternate = "userAge")
    private Integer userAge;
}
