package com.moon.arithmetic.sort;

import com.moon.util.RandomUtils;

import java.util.Arrays;

/**
 * @author yujiangtao
 * @date 2018/1/29 14:28
 */
public class TestBubbleSort {
    public static void main(String[] args) {
        int[] arr = RandomUtils.getRandomIntArray(0, 100, 50);
        System.out.println(Arrays.toString(arr));
        String sortedArray = Arrays.toString(BubbleSort.advancedBubbleSort(arr));
        String advancedSortedArray = Arrays.toString(BubbleSort.bubbleSort(arr));
        System.out.println(sortedArray);
        System.out.println(advancedSortedArray);
    }
}
