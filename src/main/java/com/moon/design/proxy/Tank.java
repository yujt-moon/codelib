package com.moon.design.proxy;

import java.util.Random;

/**
 * Created by 12919 on 2018/1/7.
 */
public class Tank implements Movable {

    public void move() {
        System.out.println("Tank moving...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
