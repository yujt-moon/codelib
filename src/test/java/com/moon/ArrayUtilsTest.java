package com.moon;


import com.moon.util.ArrayUtils;

import java.util.Arrays;

/**
 * @author yujiangtao
 * @date 2018/1/29 14:08
 */
public class ArrayUtilsTest {
    public static void main(String[] args) {
        int[] arr = {2, 1};
        System.out.println(Arrays.toString(ArrayUtils.swap(arr, 0, 1)));

        String fileName = "C:\\hehe\\haha\\a.jpg";
        String imageName = fileName.substring(fileName.lastIndexOf("\\")+1);
        System.out.println(imageName);

        System.out.println(2 == (short) 2);
    }
}
