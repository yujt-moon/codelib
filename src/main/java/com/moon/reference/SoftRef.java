package com.moon.reference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 垃圾回收不会直接删除，如果没有空间会删除，如果有空间会保留
 *
 * 软引用 -- 非常适合缓存使用
 * 加上虚拟机参数可以看到效果
 * -Xmx20M
 *
 *
 * https://www.cnblogs.com/mfrank/p/10154535.html
 *
 * @author yujiangtao
 * @date 2020/8/24 下午8:37
 */
public class SoftRef {

    public static void main(String[] args) throws IOException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());

        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        // 再分配一个数组，heap 将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会将软引用干掉。
        byte[] b = new byte[1024*1024*12];
        System.out.println(m.get());
    }
}
