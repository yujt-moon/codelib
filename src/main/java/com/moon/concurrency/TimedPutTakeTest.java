package com.moon.concurrency;

import org.junit.Assert;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多个生产者与消费者的测试，并使用计时器
 * @author yujiangtao
 * @date 2019/3/20 12:36
 */
public class TimedPutTakeTest {

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private final AtomicInteger putSum = new AtomicInteger(0);

    private final AtomicInteger takeSum = new AtomicInteger(0);

    private final CyclicBarrier barrier;

    private final BoundedBuffer<Integer> bb;

    private final BarrierTimer timer;

    /**
     * 每组试验次数，消费者和生产者的对数
     */
    private final int nTrials, nPairs;

    public TimedPutTakeTest(int capacity, int npairs, int nTrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = nTrials;
        this.nPairs = npairs;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(npairs * 2 + 1, timer);
    }

    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            // 主线程走到此处，生产者开始生产，消费者开始消费
            barrier.await(); // 等待所有的线程就绪
            barrier.await(); // 等待所有的线程执行完成
            long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
            System.out.println("Throughput: " + nsPerItem + " ns/item");
            Assert.assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param y
     * @return
     */
    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    public static void main(String[] args) {
        new TimedPutTakeTest(10, 10, 100000).test(); // 示例参数
        pool.shutdown();
    }

    /**
     * 生产者
     */
    class Producer implements Runnable {

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum = 0;
                // 生产者消费者同时在此处的等待
                barrier.await();
                for(int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                // 生产者消费者同时在此处的等待
                barrier.await();
                int sum = 0;
                for(int i = nTrials; i> 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
