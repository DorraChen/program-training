package com.example.stringformat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorra
 * @date 2021/7/15 10:59
 * @description
 */
public class MessageFormatDemo {
    /**
     * 结算时间+付款单位+收款单位+凭据编号+备注, 用“；”隔开
     */
    private static final String SETTLE_TIME = "结算时间：{0}";
    private static final String PAYER_UNIT = "付款单位：{0}";
    private static final String PAYEE_UNIT = "收款单位：{0}";
    private static final String VOUCHER_NUMBER = "凭据编号：{0}";
    private static final String REMARK = "备注：{0}";
    private static final String PUNCTUATION = "；";

    public static void main(String[] args) {
        System.out.println(remarkAppend("2021-07-08", "付款单位", "收款单位", "凭据编号", "备注"));
        System.out.println(remarkAppend(null, null, null, null, null));
        System.out.println(remarkAppend(null, "null", null, null, null));
    }

    /**
     * 结算时间+付款单位+收款单位+凭据编号+备注，用“；”隔开
     *
     * @param settleTime    结算时间
     * @param payerUnit     付款单位
     * @param payeeUnit     收款单位
     * @param voucherNumber 凭据编号
     * @param remark        备注
     * @return
     */
    private static String remarkAppend(String settleTime, String payerUnit, String payeeUnit, String voucherNumber, String remark) {
        List<String> spliceStrList = new ArrayList<>();
        messageFormat(spliceStrList, settleTime, SETTLE_TIME);
        messageFormat(spliceStrList, payerUnit, PAYER_UNIT);
        messageFormat(spliceStrList, payeeUnit, PAYEE_UNIT);
        messageFormat(spliceStrList, voucherNumber, VOUCHER_NUMBER);
        messageFormat(spliceStrList, remark, REMARK);
        if (CollectionUtils.isEmpty(spliceStrList)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < spliceStrList.size(); i++) {
            if (i != spliceStrList.size() - 1) {
                stringBuilder.append(spliceStrList.get(i)).append(PUNCTUATION);
            } else {
                stringBuilder.append(spliceStrList.get(i));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 按照特定格式组合
     *
     * @param spliceStrList
     * @param spliceStr
     * @param format
     * @return
     */
    private static List<String> messageFormat(List<String> spliceStrList, String spliceStr, String format) {
        if (StringUtils.isNotBlank(spliceStr)) {
            spliceStrList.add(MessageFormat.format(format, spliceStr));
        }
        return spliceStrList;
    }

}
