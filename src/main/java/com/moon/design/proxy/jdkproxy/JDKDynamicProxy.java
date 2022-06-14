package com.moon.design.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yujiangtao
 * @date 2020/8/17 下午9:02
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("----------- " + method.getName());
            System.out.println("before invoke ...");
            result = method.invoke(target, args);
            System.out.println("after invoke ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T getProxyInstance() {
        T t = (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
        return t;
    }
}
