package com.moon.arithmetic.leetcode;

/**
 * @author yujiangtao
 * @date 2018/12/3 17:51
 */
public class MiddleOfTwoArray {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length+nums2.length;
        int[] num = new int[length];
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < length; i++) {
            boolean flag = false;
            if(index1 < nums1.length && index2 < nums2.length) {
                flag = nums1[index1] <= nums2[index2];
            }
            if(index1 >= nums1.length) {
                flag = false;
            }
            if(index2 >= nums2.length) {
                flag = true;
            }
            if(flag) {
                num[i] = nums1[index1];
                index1 ++;
            } else {
                num[i] = nums2[index2];
                index2++;
            }
        }

        printArray(num);
        if(num.length % 2 == 1) {
            return num[num.length/2];
        } else {
            return (num[num.length/2-1]+num[num.length/2])/2d;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 6, 9};
        int[] nums2 = new int[]{2, 4, 5, 10};
        double x = findMedianSortedArrays(nums1, nums2);
        System.out.println(x);
    }
}
