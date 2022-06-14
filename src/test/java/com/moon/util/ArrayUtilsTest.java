package com.moon.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 数组工具类测试
 * @author yujiangtao
 * @date 2019/1/28 10:37
 */
public class ArrayUtilsTest {

    private int[] arr;

    @Before
    public void init() {
        arr = new int[]{1, 3, 9, 7, 6, 4, 10};
    }

    @Test
    public void testSwap() {
        // 正常情况
        int[] swaped = ArrayUtils.swap(arr, 2, 5);
        Assert.assertEquals(arr[2], 4);
        Assert.assertEquals(arr[5], 9);

        // 数组越界异常
        try {
            // 边界条件
            ArrayUtils.swap(arr, 2, 10);
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
