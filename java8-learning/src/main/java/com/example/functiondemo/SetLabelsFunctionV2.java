package com.example.functiondemo;

import com.example.enums.TicketLabelEnums;
import com.google.gson.Gson;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Dorra
 * @date 2021/9/7 14:07
 * @description 多条件组合
 */
public class SetLabelsFunctionV2 {

    public static void main(String[] args) {
        // init
        List<String> labels = new ArrayList<>();
        labels.add(TicketLabelEnums.HAVE_INITIATED.getLabel());
        labels.add(TicketLabelEnums.REPEAT.getLabel());
        labels.add(TicketLabelEnums.LARGE_AMOUNT.getLabel());
        labels.add(TicketLabelEnums.REPEATEDLY.getLabel());
        setLargeAmountFunc.apply(labels, isLargeAmountFunc.apply(new BigDecimal("100"), 100, "103"));
        System.out.println(gson.toJson(labels));
        setRepeatedlyFunc.apply(labels, isRepeatedlyFunc.apply(0, 2));
        System.out.println(gson.toJson(labels));
        setRepeatFunc.apply(labels, isRepeatFunc.apply(0, 3));
        System.out.println(gson.toJson(labels));
    }

    /**
     * 是否符合 "重复" 标签的条件: 当日同一个售后原因的工单 ≥ X 笔
     * 符合-true,不符合-false
     * <p>
     * reasonCountExistToday 当日同一个售后原因的工单数
     * repeat                配置(单位:笔)
     */
    public static BiFunction<Integer, Integer, Boolean> isRepeatFunc = (reasonCountExistToday, repeat) -> {
        // do anything...
        return reasonCountExistToday != null && reasonCountExistToday >= repeat;
    };
    /**
     * 是否符合 "重大" 标签条件: 单笔工单售后金额（含积分换算）≥ Y 元
     * 符合-true,不符合-false
     * <p>
     * afterSaleMoney    售后金额
     * afterSaleIntegral 售后积分(100积分折合人民币1元)
     * standardAmount    标准金额(配置)
     */
    public static ThreeParamFunction<BigDecimal, Integer, String, Boolean> isLargeAmountFunc = (afterSaleMoney, afterSaleIntegral, standardAmount) -> {
        afterSaleMoney = null == afterSaleMoney ? BigDecimal.ZERO : afterSaleMoney;
        BigDecimal afterSaleTotal = afterSaleMoney.add(new BigDecimal(afterSaleIntegral).divide(new BigDecimal(String.valueOf(100)), 2, BigDecimal.ROUND_DOWN));
        return afterSaleTotal.compareTo(new BigDecimal(standardAmount)) >= 0;
    };
    /**
     * 是否符合 "反复" 标签的条件: 同一个客户 ≤ Z 天发起工单的售后原因相同
     * 符合-true,不符合-false
     * <p>
     * existSameReason 同一个客户 Z 天内发起工单的售后原因相同的工单数
     * repeatedly      (单位:天)
     */
    public static BiFunction<Integer, Integer, Boolean> isRepeatedlyFunc = (existSameReason, repeat) -> {
        // use repeat do anything...
        return existSameReason != null && existSameReason >= 1;
    };
    /**
     * 设置 "重复" 标签
     */
    public static BiFunction<List<String>, Boolean, List<String>> setRepeatFunc = (labels, isMatch) -> setLabel(labels, TicketLabelEnums.REPEAT, isMatch);
    /**
     * 设置 "重大" 标签
     */
    public static BiFunction<List<String>, Boolean, List<String>> setLargeAmountFunc = (labels, isMatch) -> setLabel(labels, TicketLabelEnums.LARGE_AMOUNT, isMatch);
    /**
     * 设置 "反复" 标签
     */
    public static BiFunction<List<String>, Boolean, List<String>> setRepeatedlyFunc = (labels, isMatch) -> setLabel(labels, TicketLabelEnums.REPEATEDLY, isMatch);


    private static BiFunction<List<String>, TicketLabelEnums, Boolean> isMatchLabel = (labels, ticketLabelEnums)
            -> !CollectionUtils.isEmpty(labels) && labels.contains(ticketLabelEnums.getLabel());
    private static Function<List<String>, List<String>> ifNullReturnEmpty = list -> CollectionUtils.isEmpty(list) ? new ArrayList<>() : list;
    private static Function<List<String>, List<String>> ifNullReturnNull = list -> CollectionUtils.isEmpty(list) ? null : list;

    private static Gson gson = new Gson();


    /**
     * 设置标签
     *
     * @param labels    原标签
     * @param labelEnum 待匹配标签枚举
     * @param isMatch   是否符合待匹配标签条件
     * @return List
     */
    private static List<String> setLabel(List<String> labels, TicketLabelEnums labelEnum, boolean isMatch) {
        labels = ifNullReturnEmpty.apply(labels);
        boolean existLabel = isMatchLabel.apply(labels, labelEnum);
        if (existLabel && !isMatch) {
            labels.remove(labelEnum.getLabel());
        } else if (!existLabel && isMatch) {
            labels.add(labelEnum.getLabel());
        }
        return ifNullReturnNull.apply(labels);
    }
}
