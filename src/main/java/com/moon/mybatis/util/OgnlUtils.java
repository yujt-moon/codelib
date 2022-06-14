package com.moon.mybatis.util;

import com.moon.ognl.DefaultMemberAccess;
import ognl.*;

import java.math.BigDecimal;

/**
 * @author yujiangtao
 * @date 2020/8/5 下午3:05
 */
public class OgnlUtils {

    /**
     * 根据表达式获取值
     * @param expression
     * @return
     */
    public static Object getValue(String expression, Object paramObject) {
        // 创建一个 Ognl 上下文对象
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(OgnlUtils.class,
                new DefaultMemberAccess(true),
                new DefaultClassResolver(),
                new DefaultTypeConverter());

        context.setRoot(paramObject);
        try {
            Object ognlExpression = Ognl.parseExpression(expression);
            return Ognl.getValue(ognlExpression, context, context.getRoot());
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据表达式或者字符串
     * @param expression
     * @return
     */
    public static String getStringValue(String expression, Object paramObject) {
        Object value = getValue(expression, paramObject);
        if(value != null) {
            return String.valueOf(value);
        }
        return null;
    }

    /**
     * 通过 Ognl 表达式，去计算 boolean 类型的结果
     * @param expression
     * @param paramObject
     * @return
     */
    public static boolean evaluteBoolean(String expression, Object paramObject) {
        Object value = getValue(expression, paramObject);
        if(value instanceof Boolean) {
            return (Boolean) value;
        }
        if(value instanceof Number) {
            return new BigDecimal(String.valueOf(value)).compareTo(BigDecimal.ZERO) != 0;
        }
        return value != null;
    }
}
