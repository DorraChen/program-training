package com.example.groupby.productsale;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author dorra
 * @date 2022/3/29 17:42
 * @description
 */
@Data
public class ProductSalesDTO {
    /**
     * 雪花算法id
     */
    private Long id;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 销售区域市code
     */
    private String cityCode;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 销售面积
     */
    private BigDecimal orderArea;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

    public ProductSalesDTO() {
    }

    public ProductSalesDTO(String productId, String cityCode, String createTime) {
        this.productId = productId;
        this.cityCode = cityCode;
        this.createTime = createTime;
    }

    public ProductSalesDTO(String productId, String cityCode, String createTime, BigDecimal orderArea) {
        this.productId = productId;
        this.cityCode = cityCode;
        this.createTime = createTime;
        this.orderArea = orderArea;
    }
}
