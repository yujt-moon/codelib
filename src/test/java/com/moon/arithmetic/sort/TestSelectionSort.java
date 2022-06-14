package com.moon.arithmetic.sort;

import java.util.Arrays;

/**
 * @author yujiangtao
 * @date 2018/1/29 14:58
 */
public class TestSelectionSort {
    public static void main(String[] args) {
        //int[] arr = RandomUtils.getRandomIntArray(0, 100, 50);
        int[] arr = {2, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(SelectionSort.selectionSort(arr)));
    }
}
