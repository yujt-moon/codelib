package com.moon.design.proxy;
import java.lang.reflect.Method;
public class $Proxy1 implements com.moon.design.proxy.Movable {
    InvocationHandler h;

    public $Proxy1(InvocationHandler h) {
        super();
        this.h = h;
    }

    @Override
    public void move() {
        try {
           Method md = com.moon.design.proxy.Movable.class.getMethod("move");
           h.invoke(this, md);
        } catch(Exception e) {
           e.printStackTrace();
        }
    }
}
