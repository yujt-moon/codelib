package com.moon.util;

/**
 * 数字工具类
 * @author yujiangtao
 * @date 2018/5/11 9:21
 */
public class DigitUtils {

    /**
     * 判断数字是否是奇数
     * @param digit
     * @return
     */
    public static boolean isOdd(Long digit) {
       return (digit & 1) == 1;
    }

    /**
     * 判断数字是否是偶数
     * @param digit
     * @return
     */
    public static boolean isEven(Long digit) {
        return (digit & 1) == 0;
    }

    /**
     * 判断数字是否是奇数
     * @param digit
     * @return
     */
    public static boolean isOdd(Integer digit) {
        return (digit & 1) == 1;
    }

    /**
     * 判断数字是否是偶数
     * @param digit
     * @return
     */
    public static boolean isEven(Integer digit) {
        return (digit & 1) == 0;
    }
}
