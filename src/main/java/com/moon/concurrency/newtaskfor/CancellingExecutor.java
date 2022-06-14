package com.moon.concurrency.newtaskfor;

import java.util.concurrent.*;

/**
 * @author yujiangtao
 * @date 2019/3/10 14:56
 */
public class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if(callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }
}
