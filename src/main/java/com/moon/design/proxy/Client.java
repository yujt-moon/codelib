package com.moon.design.proxy;

/**
 * 可以对任意的对象、任意的接口对象实现任意的代理
 * Created by 12919 on 2018/1/7.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Tank t = new Tank();
        InvocationHandler h = new TimeHandler(t);
        Movable m = (Movable) Proxy.newProxyInstance(Movable.class, h);
        m.move();
    }
}
