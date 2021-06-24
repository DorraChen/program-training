package com.example.fieldresolution;

import org.springframework.util.ObjectUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dorra
 * @date 2019/12/18 11:36
 * @description 字段很多, 但是前缀都一样.考虑直接将bean转map, 简化开发
 */
public class SensorsBeanToMapUtils {
    /**
     * 神策事件属性名前缀
     */
    private static final String PREFIX = "groupBuy_e_";

    private SensorsBeanToMapUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map<String, Object> beanToMap(Object bean) {
        Class<? extends Object> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>(16);
        Field[] fieldArray = type.getDeclaredFields();
        for (Field field : fieldArray) {
            if (field.isSynthetic()) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object value = field.get(bean);
                if (ObjectUtils.isEmpty(value)) {
                    value = setDefaultValue(field, value);
                }
                returnMap.put(PREFIX + field.getName(), value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnMap;
    }

    /**
     * 设置默认值(空处理)
     *
     * @param field
     * @param value
     * @return
     */
    private static Object setDefaultValue(Field field, Object value) {
        // 为空是设置默认值
        if (field.getType().isInstance(new BigDecimal(0))) {
            value = BigDecimal.ZERO;
        }
        if (field.getType().isInstance("")) {
            value = "";
        }
        if (field.getType().isInstance(Integer.valueOf(0))) {
            value = Integer.valueOf(0);
        }
        if (field.getType().isInstance(Boolean.valueOf(false))) {
            value = Boolean.valueOf(false);
        }
        if (field.getType().isInstance(new ArrayList<>())) {
            value = new ArrayList<>();
        }
        return value;
    }
}
