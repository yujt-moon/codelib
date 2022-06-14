package com.moon.jvm;

import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2018/6/26 12:01
 */
public class EdenAllocationTest {
    private static final int _1MB = 1024 * 1024;

    @Test
    public void testEdenAllocation() {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[4 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];
    }
}
