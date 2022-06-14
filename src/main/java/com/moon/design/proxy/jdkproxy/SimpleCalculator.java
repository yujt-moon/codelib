package com.moon.design.proxy.jdkproxy;

/**
 * @author yujiangtao
 * @date 2020/8/17 下午8:55
 */
public class SimpleCalculator implements Calculator {
    @Override
    public int calculate(int x, int y) {
        System.out.println("calculating ...");
        return x + y;
    }
}
