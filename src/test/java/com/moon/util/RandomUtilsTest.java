package com.moon.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 随机工具类测试
 * @author yujiangtao
 * @date 2019/3/25 14:52
 */
public class RandomUtilsTest {

    @Test
    public void testGetRandomInt() {
        int randomInt = RandomUtils.getRandomInt(0, 1);
        System.out.println(randomInt);
        assertTrue(randomInt == 0 || randomInt == 1);

        // 边界条件测试
        try {
            RandomUtils.getRandomInt(100, 30);
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testGetRandomIntArray() {
        int[] arr = RandomUtils.getRandomIntArray(0, 100, 100);
        System.out.println(Arrays.toString(arr));
        assertEquals(100, arr.length);

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 0 || arr[i] > 100) {
                fail();
            }
        }

        // 边界条件测试
        try {
            RandomUtils.getRandomIntArray(8, 2, 20);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }
}
