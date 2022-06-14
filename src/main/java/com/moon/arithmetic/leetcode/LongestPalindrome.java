package com.moon.arithmetic.leetcode;

/**
 * @author yujiangtao
 * @date 2018/12/17 15:14
 */
public class LongestPalindrome {
    public static String longestPalindrome(String str) {
        int length = str.length();
        int maxlength = 1;
        int index = 0;
        for (int i = 0; i < length ; i++) {
            // 两边对称的形式
            if(str.charAt(i) == str.charAt(i+1)) {
                if(i - 1 >= 0 && i +2 < length) {
                    // TODO
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        longestPalindrome("babad");
    }
}
