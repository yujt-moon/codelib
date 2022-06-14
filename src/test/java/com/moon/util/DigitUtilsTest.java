package com.moon.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 数字工具类测试
 * @author yujiangtao
 * @date 2019/1/28 10:55
 */
public class DigitUtilsTest {

    /**
     * 测试是否为奇数
     * 使用三种不同的数据测试：
     * 正数、负数和零
     */
    @Test
    public void testIsOddLongType() {
        boolean isOdd = DigitUtils.isOdd(1L);
        assertTrue(isOdd);
        boolean isOdd2 = DigitUtils.isOdd(-1L);
        assertTrue(isOdd2);
        boolean isOdd3 = DigitUtils.isOdd(0L);
        assertFalse(isOdd3);
    }

    @Test
    public void testIsEvenLongType() {
        boolean isEven = DigitUtils.isEven(2L);
        assertTrue(isEven);
        boolean isEven2 = DigitUtils.isEven(-2L);
        assertTrue(isEven2);
        boolean isEven3 = DigitUtils.isEven(0L);
        assertTrue(isEven3);
    }

    @Test
    public void testIsOddIntegerType() {
        boolean isOdd = DigitUtils.isOdd(1);
        assertTrue(isOdd);
        boolean isOdd2 = DigitUtils.isOdd(-1);
        assertTrue(isOdd2);
        boolean isOdd3 = DigitUtils.isOdd(0);
        assertFalse(isOdd3);
    }

    @Test
    public void testIsEvenIntegerType() {
        boolean isEven = DigitUtils.isEven(2);
        assertTrue(isEven);
        boolean isEven2 = DigitUtils.isEven(-2);
        assertTrue(isEven2);
        boolean isEven3 = DigitUtils.isEven(0);
        assertTrue(isEven3);
    }
}
