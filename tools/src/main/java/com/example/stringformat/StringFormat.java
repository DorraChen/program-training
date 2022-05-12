package com.example.stringformat;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dorra
 * @date 2022/5/12 10:23
 * @description
 */
public class StringFormat {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = formatter.format(LocalDateTime.now());
        String format = "(''{0}'', {1}, ''{2}'', {3}, ''{4}'', {5}, ''{6}'', {7}, {8}, ''({9} 程序跑)同步高德地址:新增街道''),";
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setProvinceName("浙江省");
        addressEntity.setProvinceCode("33");
        addressEntity.setCityName("杭州市");
        addressEntity.setCityCode("3301");
        addressEntity.setCountyName("滨江区");
        addressEntity.setCountyCode("330108");
        addressEntity.setStreetName("滨江街道");
        addressEntity.setStreetCode("330108001");
        addressEntity.setEffect(1);
        // 注意MessageFormat对单引号的处理,使用双引:例如 ''{0}''
        String afterFormat = MessageFormat.format(format, addressEntity.getProvinceName(), addressEntity.getProvinceCode(),
                addressEntity.getCityName(), addressEntity.getCityCode(),
                addressEntity.getCountyName(), addressEntity.getCountyCode(),
                addressEntity.getStreetName(), addressEntity.getStreetCode(),
                addressEntity.getEffect(), time);
        System.out.println(afterFormat);
    }
}
