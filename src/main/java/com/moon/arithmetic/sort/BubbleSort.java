package com.moon.arithmetic.sort;

/**
 * 冒泡排序
 * @author yujiangtao
 * @date 2018/1/27 15:27
 */
public class BubbleSort {

    /**
     * 冒泡排序，时间复杂度O(n^2)
     * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int count = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length -1; j++) {
                count ++;
                if(arr[j] > arr[j+1]) {
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j+1] ^ arr[j];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
        }
        System.out.println("count: " + count);
        return arr;
    }

    /**
     * 优化的冒泡排序，时间复杂度O(n^2)
     * @param arr
     * @return
     */
    public static int[] advancedBubbleSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int count = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            // 如果内层没有发生交换，说明已经排序完成
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                count ++;
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                    arr[j] = arr[j] ^ arr[j + 1];
                    flag = true;
                }
            }
            if(!flag) {
                break;
            }
        }
        System.out.println("count: " + count);
        return arr;
    }
}
