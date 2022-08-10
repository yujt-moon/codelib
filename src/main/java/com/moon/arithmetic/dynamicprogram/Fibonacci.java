package com.moon.arithmetic.dynamicprogram;

import org.junit.Test;

/**
 * 使用动态规划解决斐波那契问题
 *
 * @author yujiangtaoa
 * @date 2022/8/4 下午3:37
 */
public class Fibonacci {

    @Test
    public void testdy() {
        System.out.println(Fibonacci.fib(50));
    }

    @Test
    public void testRecur() {
        System.out.println(Fibonacci.fibRecur(50));
    }

    /**
     * 使用动态规划
     * @param n
     * @return
     */
    public static long fib(int n) {
        long[] dp = new long[n+1];

        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 使用递归
     * @param n
     * @return
     */
    public static long fibRecur(int n) {
        if(n == 1 || n == 2) {
            return 1L;
        }
        return fibRecur(n-1) + fibRecur(n-2);
    }
}
