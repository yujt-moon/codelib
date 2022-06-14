package com.moon.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用 -- 垃圾收集器直接回收
 *
 * @author yujiangtao
 * @date 2020/8/24 下午9:04
 */
public class WeakRef {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}
