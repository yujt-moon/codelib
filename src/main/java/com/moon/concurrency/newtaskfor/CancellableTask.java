package com.moon.concurrency.newtaskfor;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author yujiangtao
 * @date 2019/3/10 14:52
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
