package com.moon.arithmetic.leetcode;

/**
 * @author yujiangtao
 * @date 2019/3/7 9:52
 */
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length - 1; i++) {
            for(int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if(area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int arr[] = {1,8,6,2,5,4,8,3,7};
        int area = ContainerWithMostWater.maxArea(arr);
        System.out.println(area);
    }
}
