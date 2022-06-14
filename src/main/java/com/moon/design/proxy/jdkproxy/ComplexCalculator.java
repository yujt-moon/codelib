package com.moon.design.proxy.jdkproxy;

/**
 * @author yujiangtao
 * @date 2020/8/18 下午4:35
 */
public class ComplexCalculator implements Calculator {
    @Override
    public int calculate(int x, int y) {
        return x * y;
    }
}
