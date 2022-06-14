package com.moon.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存耗时计算
 * 缺点：同步整个计算方法，多线程访问会阻塞，有时性能不如直接计算
 * @author yujiangtao
 * @date 2019/2/17 14:38
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
