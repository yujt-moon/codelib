package com.moon.concurrency;

import org.junit.Test;
import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存行相关
 *
 * @author yujiangtao
 * @date 2021/2/25 下午12:42
 */
public class CacheLine {

    private static long COUNT = 1_000_000_000L;

    @Test
    public void testCacheLine() throws Exception {

//        Subject[] subjects = new Subject[2];
//        subjects[0] = new Subject(0);
//        subjects[1] = new Subject(0);

//        PreventOptimizeObject[] subjects = new PreventOptimizeObject[2];
//        subjects[0] = new PreventOptimizeObject(0);
//        subjects[1] = new PreventOptimizeObject(0);

//        PaddingSubject[] subjects = new PaddingSubject[2];
//        subjects[0] = new PaddingSubject(0);
//        subjects[1] = new PaddingSubject(0);

        ContendedObject[] subjects = new ContendedObject[2];
        subjects[0] = new ContendedObject(0);
        subjects[1] = new ContendedObject(0);

        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                subjects[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                subjects[1].x = i;
            }
            latch.countDown();
        });

        long startTime = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1_000_000 + "s");
    }

    /**
     * 普通对象
     */
    private static class Subject {
        private volatile long x;

        public Subject(long x) {
            this.x = x;
        }
    }

    /**
     * 被前后7个 long 包围的对象
     */
    private static class PaddingSubject {
        private long p1, p2, p3, p4, p5, p6, p7;
        private volatile long x;
        private long p9, p10, p11, p12, p13, p14, p15;

        public PaddingSubject(long x) {
            this.x = x;
        }
    }

    private static abstract class AbstractPaddingObject {
        protected long p1, p2, p3, p4, p5, p6, p7;
    }

    /**
     * prevent cache line false share（伪共享）
     */
    private static class PreventOptimizeObject extends AbstractPaddingObject {
        private volatile long x;

        private long p9, p10, p11, p12, p13, p14, p15;

        public PreventOptimizeObject(long x) {
            this.x = x;
        }
    }

    /**
     * -XX:-RestrictContended
     */
    @Contended
    private static class ContendedObject {
        private volatile long x;

        public ContendedObject(long x) {
            this.x = x;
        }
    }

    @Test
    public void testArr() {
        long[][] arr = new long[1024 * 1024][8];
        long sum = 0;
        // 横向遍历
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        marked = System.currentTimeMillis();
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}
