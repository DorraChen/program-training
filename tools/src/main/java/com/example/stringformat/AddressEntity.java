package com.example.stringformat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dorra
 * @date 2022/2/28 14:35
 * @description 四级地址实体
 */
@Setter
@Getter
public class AddressEntity {
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 省code
     */
    private String provinceCode;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 市code
     */
    private String cityCode;
    /**
     * 区名称
     */
    private String countyName;
    /**
     * 区code
     */
    private String countyCode;
    /**
     * 街道名称
     */
    private String streetName;
    /**
     * 街道code
     */
    private String streetCode;
    /**
     * 是否开通(0-未开通，1-已开通)
     */
    private int effect;
}
