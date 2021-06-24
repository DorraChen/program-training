package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Dorra
 * @data 2020/9/29 17:22
 * @description 精度测试
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Long mIntegral = 213103L;
        BigDecimal mOrigin = new BigDecimal("2131.08");
        BigDecimal zOrigin1 = new BigDecimal("12.03");
        BigDecimal zOrigin2 = new BigDecimal("100");
        BigDecimal zOrigin3 = new BigDecimal("20.05");
        BigDecimal zOrigin4 = new BigDecimal("1999");

        Long zIntegral1 = zOrigin1.divide(mOrigin, 32, RoundingMode.HALF_UP)
                // 一开始这里保留4位, 计算之后精度不准
                .multiply(new BigDecimal(mIntegral))
                .setScale(0, RoundingMode.HALF_UP).longValue();
        // 这里注意一个细节: https://blog.csdn.net/m0_37772518/article/details/108381342
        BigDecimal zAmount1 = zOrigin1.subtract(new BigDecimal(zIntegral1 / 100.0)
                .setScale(2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);

        Long zIntegral2 = zOrigin2.divide(mOrigin, 32, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(mIntegral))
                .setScale(0, RoundingMode.HALF_UP).longValue();
        BigDecimal zAmount2 = zOrigin2.subtract(new BigDecimal(zIntegral2 / 100.0)
                .setScale(2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);

        Long zIntegral3 = zOrigin3.divide(mOrigin, 32, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(mIntegral))
                .setScale(0, RoundingMode.HALF_UP).longValue();
        BigDecimal zAmount3 = zOrigin3.subtract(new BigDecimal(zIntegral3 / 100.0)
                .setScale(2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);

        Long zIntegral4 = mIntegral - zIntegral1 - zIntegral2 - zIntegral3;
        BigDecimal zAmount4 = zOrigin4.subtract(new BigDecimal(zIntegral4 / 100.0)
                .setScale(4, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println("zIntegral1: " + zIntegral1);
        System.out.println("zAmount1: " + zAmount1);
        System.out.println("zIntegral2: " + zIntegral2);
        System.out.println("zAmount2: " + zAmount2);
        System.out.println("zIntegral3: " + zIntegral3);
        System.out.println("zAmount3: " + zAmount3);
        System.out.println("zIntegral4: " + zIntegral4);
        System.out.println("zAmount4: " + zAmount4);
        System.out.println("耗时: " + (System.currentTimeMillis() - time));
    }
}
