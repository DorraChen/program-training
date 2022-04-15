package com.example.feishu.niotice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @author dorra
 * @date 2022/4/12 14:25
 * @description 飞书返回结果
 */
@Data
public class FeiShuResult {
    protected static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static final Integer SUCCESS_CODE = 0;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("msg")
    private String msg;

    public boolean isSuccess() {
        if (this.code != null) {
            return SUCCESS_CODE.equals(code);
        } else {
            return false;
        }
    }
}
