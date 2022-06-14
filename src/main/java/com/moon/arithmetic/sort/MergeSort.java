package com.moon.arithmetic.sort;

import java.util.Arrays;

/**
 * 归并排序 O(logn)
 * @author yujiangtao
 * @date 2020/7/6下午9:25
 */
public class MergeSort implements Sortable {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 2, 4, 9, 3, 6};
        int[] sort = new MergeSort().sort(arr);
        System.out.println("排序后的数组：" + Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] arr) {
        int length = arr.length;
        if(length > 1) {
            int medium = length / 2 + (length % 2);
            int[] leftArr = new int[medium];
            int[] rightArr = new int[length - medium];
            // Arrays.copyOfRange 也可以拷贝
            System.arraycopy(arr, 0, leftArr, 0, medium);
            System.arraycopy(arr, medium, rightArr, 0, length - medium);
            System.out.println("左侧数组：" + Arrays.toString(leftArr));
            System.out.println("右侧数组：" + Arrays.toString(rightArr));
            System.out.println("=====================================");
            arr = merge(sort(leftArr), sort(rightArr));
        }
        return arr;
    }

    /**
     * 对排序好的数组，进行合并
     * @param left
     * @param right
     * @return
     */
    private int[] merge(int[] left, int[] right) {
        int[] orderedArr = new int[left.length + right.length];
        int leftIdx = 0;
        int rightIdx = 0;
        for (int i = 0; i < left.length + right.length; i++) {
            // 是否两个数组都未取完数据
            if(leftIdx < left.length && rightIdx < right.length) {
                if(left[leftIdx] <= right[rightIdx]) {
                    orderedArr[i] = left[leftIdx++];
                } else {
                    orderedArr[i] = right[rightIdx++];
                }
            } else {
                // 如果左侧的数组没有取完数据
                if(leftIdx < left.length) {
                    orderedArr[i] = left[leftIdx++];
                }
                // 如果右侧的数组没有取完数据
                if(rightIdx < right.length) {
                    orderedArr[i] = right[rightIdx++];
                }
            }
        }
        System.out.println("部分排序过的：" + Arrays.toString(orderedArr));
        return orderedArr;
    }
}
