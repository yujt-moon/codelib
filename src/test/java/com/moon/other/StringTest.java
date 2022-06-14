package com.moon.other;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 字符串对象测试
 * @author yujiangtao
 * @date 2018/2/22 11:14
 */
public class StringTest {

    @Test
    public void testSameString() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";

        assertFalse(s1 == s2);
        assertTrue(s1 == s3);
        assertTrue(s1 == s1.intern());
    }
}
