package com.moon.design.proxy;

/**
 * 继承拓展坦克的move的功能
 * @author yujiangtao
 */
public class Tank2 extends Tank {

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        super.move();
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start));
    }
}
