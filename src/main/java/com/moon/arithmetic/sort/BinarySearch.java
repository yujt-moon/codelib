package com.moon.arithmetic.sort;

/**
 * 二分法查找
 * @author yujiangtao
 * @date 2018/3/18 15:41
 */
public class BinarySearch {

    /**
     * 二分法查找
     * @param sortedArr
     * @param key
     * @return
     */
    public static int search(int[] sortedArr, int key) {
        int start = 0;
        int end = sortedArr.length -1;
        while(start <= end) {
            int middle = (start + end) / 2;
            if(key < sortedArr[middle]) {
                end = middle -1;
            } else if(key > sortedArr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sortedArr = new int[] {1, 2, 4, 7, 10, 13, 20, 33, 36, 39, 40};
        int index = search(sortedArr, 36);
        System.out.println(index);
    }
}
