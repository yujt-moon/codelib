package com.moon.reference;

import java.io.IOException;

/**
 * 强引用
 *
 * @author yujiangtao
 * @date 2020/8/24 下午8:26
 */
public class NormalRef {
    public static void main(String[] args) throws IOException {
        // 强引用
        M m = new M();
        m = null;
        System.gc(); // DisableExplicitGC
        System.out.println(m);

        System.in.read(); // 阻塞 main 线程，给垃圾回收线程时间执行
    }
}
