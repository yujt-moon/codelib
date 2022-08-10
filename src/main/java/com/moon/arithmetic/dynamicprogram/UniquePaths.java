package com.moon.arithmetic.dynamicprogram;

import org.junit.Test;

/**
 * @author yujiangtaoa
 * @date 2022/8/3 下午12:15
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) { // row: top to bottom
            for (int j = 0; j < n; j++) { // column: left to right
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else {
                    f[i][j] = f[i-1][j] + f[i][j-1];
                }
            }
        }
        return f[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 1 || obstacleGrid[0][0] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if(i == 0 || j == 0) {
                    if(obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                    }else if(i == 0 && j > 0 && dp[i][j-1] == 0) {
                        dp[i][j] = 0;
                    } else if(j== 0 && i > 0 && dp[i-1][j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    @Test
    public void solution() {
        int[][] arr = new int[][]{{0, 0},{1, 1}, {0, 0}};
        System.out.println(new UniquePaths().uniquePathsWithObstacles(arr));
    }
}
