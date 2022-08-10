package com.moon.arithmetic.dynamicprogram;

/**
 * @author yujiangtaoa
 * @date 2022/7/27 上午10:09
 */
public class CutSteelBarBotton2Top {

    public static int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /* 自底向上法 */
    private static int bottom_up_cut_rod(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                int res = j >= 10 ? 30 : prices[j-1];
                max = Math.max(max, res + dp[i-j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(bottom_up_cut_rod(53));
    }
}
