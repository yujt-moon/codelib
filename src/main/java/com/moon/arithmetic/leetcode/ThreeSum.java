package com.moon.arithmetic.leetcode;

import java.util.*;

/**
 * @author yujiangtao
 * @date 2019/3/8 16:21
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        // 数组排序，减小算法的复杂度
        Arrays.sort(nums);

        // 最小正整数的位置
        int positiveMinIndex = -1;
        // 最大负整数的位置
        int negativeMaxIndex = -1;
        // 寻找最小正整数
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                positiveMinIndex = i;
                break;
            }
        }

        // 寻找最大负整数
        for(int i = nums.length -1; i >= 0; i--) {
            if(nums[i] < 0) {
                negativeMaxIndex = i;
                break;

            }
        }

        int thirdIndex = -1;

        // 是否同时有正整数和负整数
        if(positiveMinIndex != -1 && negativeMaxIndex != -1) {
            // 外层从最小负整数到最大负整数
            for (int i = 0; i <= negativeMaxIndex; i++) {
                // 如果有同样的负整数就跳过
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 内层从最小的正整数开始到最大的正整数
                for (int j = positiveMinIndex; j < nums.length; j++) {
                    // 如果有同样的正整数就跳过
                    if (nums[j] == nums[j - 1] && j > positiveMinIndex) {
                        continue;
                    }
                    if ((thirdIndex = Arrays.binarySearch(nums, -(nums[i] + nums[j]))) >= 0) {
                        if (i == thirdIndex || j == thirdIndex) {
                            thirdIndex++;
                            if(thirdIndex >= nums.length) {
                                continue;
                            }
                            if(nums[thirdIndex] != -(nums[i] + nums[j])) {
                                continue;
                            }
                        } else if(thirdIndex < i){
                            continue;
                        }
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[thirdIndex]);
                        Collections.sort(list);
                        lists.add(list);
                    }
                }
            }
        }
        // 如果不是同时拥有正整数和负整数，则只有具有三个零才有满足的数组
        else {
            if(nums.length >=3 && nums[0] == 0 && nums[1] == 0 && nums[2] == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(0);
                list.add(0);
                list.add(0);
                lists.add(list);
                return lists;
            }
        }

        for(int i = 0; i < lists.size(); i++) {
            if(i < lists.size() - 1 && lists.get(i).get(0) == lists.get(i+1).get(0)) {
                if(lists.get(i).get(1) > lists.get(i+1).get(1)) {
                    List<Integer> temp = lists.get(i);
                    lists.set(i, lists.get(i+1));
                    lists.set(i+1, temp);
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        // 数组排序，减小算法的复杂度
        Arrays.sort(nums);

        int maxNegativeOrZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                maxNegativeOrZeroIndex = i - 1;
                break;
            }
        }


        Map<String, Integer[]> map = new TreeMap<>();
        // 外层循环选择负数或者零
        for (int i = 0; i < maxNegativeOrZeroIndex; i++) {
            // 内层循环选择正数或者零
            for (int j = maxNegativeOrZeroIndex; j < nums.length; j++) {
                int third = - (nums[i] + nums[j]);
                int thirdIndex = Arrays.binarySearch(nums, i + 1, nums.length - 1, third);
                if(thirdIndex > 0 && (thirdIndex != j || (nums[thirdIndex+1] == nums[thirdIndex+1] && thirdIndex < nums.length -1 ))) {
                    Integer arr[] = new Integer[]{nums[i], nums[j], nums[thirdIndex]};
                    Arrays.sort(arr);
                    String key = arr[0] + "," + arr[1] + "," + arr[2];
                    map.put(key, arr);
                }
            }
        }

        Iterator<Map.Entry<String, Integer[]>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            List<Integer> list = new ArrayList<>();
            Integer[] value = iterator.next().getValue();
            list.add(value[0]);
            list.add(value[1]);
            list.add(value[2]);
            lists.add(list);
        }

        return lists;
    }

    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        //int[] nums = {0, 0, 0};
        //int[] nums = {-1,0,1};
        //int[] nums = {1, 1, -2};
        //int[] nums = {-1,0,1,2,-1,-4};
        //int[] nums = {-2,0,1,1,2};
        //int[] nums = {3,0,-2,-1,1,2};
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        ThreeSum.threeSum2(nums);
    }
}
