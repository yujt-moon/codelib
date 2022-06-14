package com.moon.arithmetic.sort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 *
 * 选择排序
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
 * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 *
 *
 * @author yujiangtao
 * @date 2018/1/27 16:33
 */
public class SelectionSort {

    /**
     * 选择排序，时间复杂度O(n^2)
     * 基本思想：
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        // 边界检查
        if(arr.length < 1) {
            return null;
        }
        for(int i = 0; i < arr.length; i++) {
            int index = i;
            for(int j = i; j < arr.length; j++) {
                if(arr[index] > arr[j]) {
                    index = j;
                }
            }
            if(index != i) {
                ArrayUtils.swap(arr, index, i);
            }
        }
        return arr;
    }


    public static void sort(int[] arr) {
        int length = arr.length;

        if(length <= 1) {
            return;
        }

        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 1, 3, 2};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
