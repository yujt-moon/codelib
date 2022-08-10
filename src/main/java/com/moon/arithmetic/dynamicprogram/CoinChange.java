package com.moon.arithmetic.dynamicprogram;

/**
 *
 *
 * @author yujiangtaoa
 * @date 2022/8/3 上午11:50
 */
public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2, 5, 7}, 27));
    }

    /**
     *
     * @param a {2, 5, 7}
     * @param m 27
     * @return
     */
    public static int coinChange(int[] a, int m) {
        // 0...n: [n+1]
        // 0...n-1: [n]
        int[] f = new int[m+1];
        int n = a.length; // number of kinds of coins

        // initialization
        f[0] = 0;
        int i, j;
        // f[1], f[2], ... f[m]
        for (i = 1; i <= m; i++) {
            f[i] = Integer.MAX_VALUE;
            // last coin a[j]
            // f[i] = min(f[i-a[0]]+1, ... , f[i-a[n-1]]+1)
            for(j = 0; j < n; j++) {
                if (i >= a[j] && f[i - a[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - a[j]] + 1, f[i]);
                }
            }
        }

        if (f[m] == Integer.MAX_VALUE) {
            f[m] = -1;
        }
        return f[m];
    }
}
