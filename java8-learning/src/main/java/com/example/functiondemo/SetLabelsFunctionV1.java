package com.example.functiondemo;

import com.example.functiondemo.enums.TicketLabelEnums;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorra
 * @date 2021/9/7 14:07
 * @description 多条件组合
 */
public class SetLabelsFunctionV1 {

    public static void main(String[] args) {

    }

    /**
     * 设置标签
     *
     * @param labels    原标签
     * @param labelEnum 待匹配标签枚举
     * @param isMatch   是否符合待匹配标签条件
     * @return List
     */
    public List<String> setLabel(List<String> labels, TicketLabelEnums labelEnum, boolean isMatch) {
        labels = CollectionUtils.isEmpty(labels) ? new ArrayList<>() : labels;
        String label = labelEnum.getLabel();
        boolean existLabel = labels.contains(label);
        if (existLabel && !isMatch) {
            labels.remove(label);
        } else if (!existLabel && isMatch) {
            labels.add(label);
        }
        return CollectionUtils.isEmpty(labels) ? null : labels;
    }

    /**
     * 是否符合 "重大" 标签条件: 单笔工单售后金额（含积分换算）≥ Y 元
     * 符合-true,不符合-false
     *
     * @param afterSaleMoney    售后金额
     * @param afterSaleIntegral 售后积分(100积分折合人民币1元)
     * @param standardAmount    标准金额(配置)
     * @return boolean
     */
    private boolean isLargeAmount(BigDecimal afterSaleMoney, int afterSaleIntegral, String standardAmount) {
        afterSaleMoney = null == afterSaleMoney ? BigDecimal.ZERO : afterSaleMoney;
        BigDecimal afterSaleTotal = afterSaleMoney.add(new BigDecimal(afterSaleIntegral).divide(new BigDecimal(String.valueOf(100)), 2, BigDecimal.ROUND_DOWN));
        return afterSaleTotal.compareTo(new BigDecimal(standardAmount)) >= 0;
    }

    /**
     * 是否符合 "重复" 标签的条件: 当日同一个售后原因的工单 ≥ X 笔
     * 符合-true,不符合-false
     *
     * @param reasonCountExistToday 当日同一个售后原因的工单数
     * @param repeat                配置(单位:笔)
     * @return
     */
    private boolean isRepeat(Integer reasonCountExistToday, int repeat) {
        return reasonCountExistToday != null && reasonCountExistToday >= repeat;
    }

    /**
     * 是否符合 "反复" 标签的条件: 同一个客户 ≤ Z 天发起工单的售后原因相同
     * 符合-true,不符合-false
     *
     * @param existSameReason 同一个客户 Z 天内发起工单的售后原因相同的工单数
     * @param repeatedly      (单位:天)
     * @return
     */
    private boolean isRepeatedly(Integer existSameReason, int repeatedly) {
        return existSameReason != null && existSameReason >= 1;
    }
}
