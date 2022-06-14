package com.moon.arithmetic.sort;

import java.util.Arrays;

/**
 * 插入排序算法
 * @author yujiangtao
 * @date 2018/1/22 20:18
 */
public class InsertionSort {

    /**
     *
     * 基本思想：
     * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，
     * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr) {
        int length = arr.length;
        // 边界检查
        if (length < 2) {
            return arr;
        }
        for(int i = 1; i < length; i++) {
            int temp = arr[i];
            int j = i - 1;
            // 查找插入位置
            for(; j >= 0; j--) {
                if(arr[j] > temp) {
                    // 数据移动
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            // 插入数据
            arr[j+1] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 1, 3, 2};
        InsertionSort.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
