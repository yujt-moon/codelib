package com.moon.arithmetic.sort;

import java.util.Arrays;

/**
 * 快排
 *
 * @author yujiangtao
 * @date 2020/10/19 下午8:46
 */
public class QuickSort {

    public static void sort(int[] arr) {
        partion(arr, 0, arr.length - 1);
    }

    public static void partion(int[] arr, int start, int end) {
        if(end - start == 0) {
            return;
        }
        // 选择 start 作为分区点 pivot
        int pivotValue = arr[start];
        int i = start;
        for (int j = start + 1; j <= end; j++) {
            if(arr[j] < pivotValue) {
                // 将 i+1 的元素和 j 元素互换
                int temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            }
        }
        // 已经完成一次分区，将分区的元素换到中间
        arr[start] = arr[i];
        arr[i] = pivotValue;

        System.out.println(Arrays.toString(arr));

        // 递归分区，处理子问题
        if(i > start) {
            partion(arr, start, i - 1);
        }

        if(i + 1 < end) {
            partion(arr, i + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 10, 13, 5, 8, 3, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
