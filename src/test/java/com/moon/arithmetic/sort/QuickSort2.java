package com.moon.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * TODO
 * @author yujiangtaoa
 * @date 2022/7/17 下午10:46
 */
public class QuickSort2 {

    @Test
    public void sort() {
        int[] arr = new int[]{6, 10, 13, 5, 8, 3, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr) {
        partion(arr, 0, arr.length - 1);
    }

    private void partion(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        // 选择基准值
        int base = arr[start];
        int i = start;
        for (int j = start; j <= end ; j++) {
            if(base > arr[j]) {
                arr[i] = arr[j];
                i = j;
            }
        }
        arr[i] = base;
        if(i - start > 1) {
            partion(arr, start, i-1);
        }
        if(end - i > 1) {
            partion(arr, i+1, end);
        }
    }
}
