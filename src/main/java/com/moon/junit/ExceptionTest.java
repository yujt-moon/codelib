package com.moon.junit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yujiangtaoa
 * @date 2022/4/28 上午11:13
 */
public class ExceptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void testForException() {
        ExceptionTest.throwIllegalArgumentException();
    }

    @Test
    public void testForException2() {
        try {
            ExceptionTest.throwIllegalArgumentException();
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // ignore, this exception is expected
        }
    }

    public static void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }
}
