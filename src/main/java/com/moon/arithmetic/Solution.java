package com.moon.arithmetic;

/**
 * @author yujiangtaoa
 * @date 2022/8/5 下午1:49
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isInterleave("db", "b", "cbb"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        if(m + n != s3.length()) {
            return false;
        }

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if((i == 0 && s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]) || (j == 0 && s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j])) {
                    dp[i][j] = true;
                } else if((j > 0 && dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)) ||
                        (i > 0 && dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
}
