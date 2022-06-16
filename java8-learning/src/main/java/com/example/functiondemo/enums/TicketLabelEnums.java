package com.example.functiondemo.enums;

/**
 * @author Dorra
 * @date 2021/9/6 17:42
 * @description 工单标签枚举
 */
public enum TicketLabelEnums {
    /**
     * 发起过: 单笔订单发起工单 ≥ 2 笔；
     * 重复: 当日同一个售后原因的工单 ≥ X 笔；
     * 重大: 单笔工单售后金额（含积分换算）≥ Y 元
     * 反复: 同一个客户 ≤ Z 天发起工单的售后原因相同；
     */
    HAVE_INITIATED("1", "发起过"),
    REPEAT("2", "重复"),
    LARGE_AMOUNT("3", "重大"),
    REPEATEDLY("4", "反复"),
    ;
    private String label;
    private String name;

    TicketLabelEnums(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }
}
