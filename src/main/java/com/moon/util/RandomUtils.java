package com.moon.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author yujiangtao
 * @date 2018/1/22 20:40
 * @since 1.0
 */
public class RandomUtils {

    /**
     * 私有化工具类构造器（防止创建实例调用方法）
     */
    private RandomUtils() {}

    /**
     * 获取指定范围的int随机数
     * @param start 起始值
     * @param end 终止值
     * @return 返回一个int随机数
     */
    public static int getRandomInt(int start, int end) {
        // 边界检查
        if(start < 0 || end < 1 || end - start < 1) {
            throw new IllegalArgumentException("参数异常");
        }
        Random rand = new Random();
        return rand.nextInt(end - start + 1) + start;
    }

    /**
     * 随机生成指定范围内，指定大小的int数组
     * @param start 起始值
     * @param end 终止值
     * @param size 大小
     * @return 返回一个int[]随机数组
     */
    public static int[] getRandomIntArray(int start, int end, int size) {
        // 边界检查
        if(start < 0 || end < 1 || end < start || size < 1) {
            throw new IllegalArgumentException("参数异常");
        }
        Random random = new Random();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = random.nextInt(end - start + 1) + start;
        }
        return arr;
    }
}
