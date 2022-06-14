package com.moon.design.proxy;

import java.lang.reflect.Method;

/**
 * @author yujiangtao
 * @since 1.0
 */
public class TimeHandler implements InvocationHandler {

    public TimeHandler(Object target) {
        this.target = target;
    }

    private Object target;

    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        System.out.println("starttime:" + start);
        System.out.println(o.getClass().getName());
        try {
            m.invoke(target, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start));
    }
}
