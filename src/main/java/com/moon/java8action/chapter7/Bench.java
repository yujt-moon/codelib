package com.moon.java8action.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;

public class Bench {

    /**
     * 测试求和性能
     * @param adder
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if(duration < fastest)
                fastest = duration;
        }
        return fastest;
    }
}
