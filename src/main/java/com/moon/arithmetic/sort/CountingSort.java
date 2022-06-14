package com.moon.arithmetic.sort;

/**
 * 计数排序
 * @author yujiangtao
 * @date 2020/7/10 上午10:50
 */
public class CountingSort {
    int[] arr = new int[]{8, 2, 4, 9, 3, 6};

    /**
     * 找到数组中的最大最小值
     * @param arr
     * @return
     */
    public MaxMin findMaxMin(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
            }
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        return new MaxMin(max, min);
    }

    class MaxMin {
        private int max;

        private int min;

        public MaxMin(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }
}
