package com.moon.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用 -- 虚拟机会将将要回收的线程放到 ReferenceQueue 中，如果队列中有
 * -Xmx20M
 *
 * 作用：管理直接内存（堆外内存）
 * ByteBuffer.allocateDirect(1024)
 *
 * @author yujiangtao
 * @date 2020/8/24 下午9:09
 */
public class PhantomRef {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        System.out.println(phantomReference.get());

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        // 垃圾回收线程
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if(poll != null) {
                    System.out.println("--- 虚引用对象被 jvm 回收了 ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
