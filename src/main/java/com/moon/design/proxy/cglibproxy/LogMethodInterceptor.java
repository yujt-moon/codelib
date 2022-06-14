package com.moon.design.proxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yujiangtao
 * @date 2020/8/18 下午5:28
 */
public class LogMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke ...");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after invoke ...");
        return result;
    }
}
