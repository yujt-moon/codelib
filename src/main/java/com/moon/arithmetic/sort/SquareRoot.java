package com.moon.arithmetic.sort;

import java.math.BigDecimal;

/**
 * 求取平方根
 *
 * @author yujiangtao
 * @date 2020/10/20 下午7:09
 */
public class SquareRoot {

    /**
     * 二分法求取平方根
     * @param input
     * @return
     */
    public static double binaryGet(double input) {
        double start = 0;
        double end = input > 1.0 ? input : 1.0;
        double mid = (start + end) / 2;
        while(true) {
            double times = mid * mid;
            if(times > input) {
                end = mid;
                mid = (start + end) / 2;
            } else if(times < input && input - times > 0.000001) {
                start = mid;
                mid = (start + end) / 2;
            } else {
                break;
            }
        }
        BigDecimal b = new BigDecimal(mid);
        return b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(binaryGet(7.0));
        System.out.println(binaryGet(9.0));
        System.out.println(binaryGet(0.25));
        System.out.println(binaryGet(1.0));
    }
}
