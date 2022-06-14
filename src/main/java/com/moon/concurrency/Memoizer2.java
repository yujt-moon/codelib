package com.moon.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存耗时计算
 * 缺点：无法知道是否同时存在多个相同的耗时计算
 * @author yujiangtao
 * @date 2019/2/17 14:44
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
