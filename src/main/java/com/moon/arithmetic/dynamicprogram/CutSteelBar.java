package com.moon.arithmetic.dynamicprogram;

import org.junit.Test;

/**
 * 切钢条
 *
 * @author yujiangtaoa
 * @date 2022/7/26 下午10:07
 */
public class CutSteelBar {

    public static int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    @Test
    public void steelBarRecur() {
        System.out.println(CutSteelBar.recur(33));
    }

    @Test
    public void steelBarDyn() {
        System.out.println(CutSteelBar.dyn(33));
    }

    /**
     * 递归
     * @param length
     * @return
     */
    public static int recur(int length) {
        if (length == 0) return 0;
        int result = 0;
        for (int i = 1; i <= prices.length; i++) {
            if(length >= i) {
                result = Math.max(result, prices[i - 1] + recur(length - i));
            }
        }
        return result;
    }

    /**
     * 使用动态规划
     * @param length
     * @return
     */
    public static int dyn(int length) {
        int[] dp = new int[length+1];
        dp[0] = 0;

        for (int i = 0; i <= length; i++) {
            for (int j = 0; j < prices.length ; j++) {
                if (i - j - 1 >= 0) {
                    dp[i] = Math.max(dp[i - j - 1] + prices[j], dp[i]);
                }
            }
        }

        return dp[length];
    }
}
