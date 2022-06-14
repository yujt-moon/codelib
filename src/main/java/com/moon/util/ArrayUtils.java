package com.moon.util;

/**
 * 数组工具类
 * @author yujiangtao
 * @date 2018/1/22 20:36
 */
public class ArrayUtils {

    /**
     * 无法被实例化
     */
    private ArrayUtils() {}

    /**
     * 交换数组中的元素
     * @param arr 要被交换的元素
     * @param swapIndexA 要交换的索引
     * @param swapIndexB 要交换的索引
     * @return
     */
    //@Deprecated
    public static int[] swap(int[] arr, int swapIndexA, int swapIndexB) {
        // 边界检查
        if(arr.length < 2 || swapIndexA > arr.length - 1 ||
                swapIndexB > arr.length -1 || swapIndexA == swapIndexB) {
            throw new IllegalArgumentException("参数非法");
        }

        // 性能似乎没有引入第三个变量高（不推荐使用）
        arr[swapIndexA] = arr[swapIndexA] ^ arr[swapIndexB];
        arr[swapIndexB] = arr[swapIndexA] ^ arr[swapIndexB];
        arr[swapIndexA] = arr[swapIndexA] ^ arr[swapIndexB];
        return arr;
    }
}
