package com.moon.jvm;

import org.junit.Test;

/**
 *
 * VM: -verbose:gc
 * @author yujiangtao
 * @date 2021/3/13 下午9:02
 */
public class LocalSlotReuse {

    @Test
    public void gcArraySlot() {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }

    @Test
    public void gcOtherScope() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }

    @Test
    public void gcArraySlotSuccess() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
