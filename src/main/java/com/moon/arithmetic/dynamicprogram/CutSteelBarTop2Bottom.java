package com.moon.arithmetic.dynamicprogram;

import java.util.Arrays;

/**
 * @author yujiangtaoa
 * @date 2022/7/27 上午9:55
 */
public class CutSteelBarTop2Bottom {

    public static int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /* 自顶向下 */
    public static int mem_cut_rod(int n) {
        int[] dp = new int[n+1];        // 辅助数组dp
        Arrays.fill(dp, Integer.MIN_VALUE); // 初始化为负无穷
        return mem_cut_rod_aux(n, dp);
    }

    /* 自顶向下的辅助函数 */
    private static int mem_cut_rod_aux(int n, int[] dp) {
        if (dp[n] >= 0)
            return dp[n];   // 如果子问题已经解过，直接返回
        int max = Integer.MIN_VALUE;
        if (n == 0)
            max = 0;        // 如果长度为0, 则最大收益为0
        else {
            for (int i = 1; i <= n; i++) {      // 找到最大收益
                int res = i >=10 ? 30 : prices[i-1];
                max = Math.max(max, res + mem_cut_rod_aux(n-i, dp));
            }
        }
        dp[n] = max;        // 把计算得到的最大收益存入结果
        return max;
    }

    public static void main(String[] args) {
        System.out.println(mem_cut_rod(53));
    }
}
