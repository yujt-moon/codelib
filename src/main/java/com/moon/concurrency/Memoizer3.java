package com.moon.concurrency;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 缓存耗时计算
 * 缺点：很低概率发生多个同时计算（f==null的判断）
 * @author yujiangtao
 * @date 2019/2/17 14:59
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, f);
            // 在这里将调用c.compute
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw ExceptionUtils.launderThrowable(e.getCause());
        }
    }

}
