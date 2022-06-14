package com.moon.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象转集合工具类
 * @author yujiangtao
 * @date 2018/9/25 16:01
 * @since 1.0
 */
public class Object2MapUtil {

    /**
     * 利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}


