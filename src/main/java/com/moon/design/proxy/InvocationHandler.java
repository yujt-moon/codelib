package com.moon.design.proxy;

import java.lang.reflect.Method;

/**
 * Created by 12919 on 2018/1/8.
 */
public interface InvocationHandler {
    public void invoke(Object o, Method method);
}
