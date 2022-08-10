package com.moon.arithmetic.dynamicprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * 交替字符串
 *
 * 给定三个字符串 s1，s2，s3,验证 s3 是否由 s1 和 s2 交错组成的。
 * 示例1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 *
 * @author yujiangtaoa
 * @date 2022/7/30 上午9:43
 */
public class AlternatelyString {

    static String s1 = "123";
    static String s2 = "34";
    static String s3 = "12343";
    static List<Integer[]> list = new ArrayList<>();

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        System.out.println(isInterleave(s1, s2, s3));
        System.out.println(isInterleave2(s1, s2, s3));
        System.out.println(isInterleave2("aabcc", "dbbca", "aadbbbaccc"));
    }

    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;

        for(int i = 0; i < s1.length() + 1; i++) {
            for(int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if(((i > 0 && dp[i-1][j] &&
                        s1.charAt(i-1) == s3.charAt(i+j-1)) || ( j > 0 &&dp[i][j-1] &&
                        s2.charAt(j-1) == s3.charAt(i+j-1)))) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    /**
     * 其他人实现
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3){
        int n = s1.length(), m = s2.length(), s = s3.length();

        //如果长度不一致，则s3不可能由s1和s2交错组成
        if (n + m != s)
            return false;

        boolean[][]dp = new boolean[n + 1][m + 1];

        //在初始化边界时，我们认为空串可以由空串组成，因此dp[0][0]赋值为true。
        dp[0][0] = true;

        for (int i = 0; i < n + 1; i++){
            for (int j = 0; j < m + 1; j++){
                if ( dp[i][j] || (i - 1 >= 0 && dp[i - 1][j] == true &&
                        //取s1字符
                        s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||

                        (j - 1 >= 0 && dp[i][j - 1] == true &&
                                //取s2字符
                                s2.charAt(j - 1) == s3.charAt(i + j - 1)) )

                    dp[i][j] = true;
                else
                    dp[i][j] = false;
            }
        }
        return dp[n][m];
    }
}
