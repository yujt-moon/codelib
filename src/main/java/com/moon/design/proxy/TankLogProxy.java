package com.moon.design.proxy;

/**
 * Created by 12919 on 2018/1/7.
 */
public class TankLogProxy implements Movable {
    Movable t;

    public TankLogProxy(Movable m) {
        super();
        this.t = m;
    }

    @Override
    public void move() {
        System.out.println("Tank Start...");
        t.move();
        System.out.println("Tank Stop...");
    }
}
