package com.moon.concurrency.gui;

import java.util.concurrent.*;

/**
 * @author yujiangtao
 * @date 2019/3/17 10:34
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {

    private final FutureTask<V> computation = new Computation();

    @Override
    public void run() {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    // 在后台线程中被取消
    protected abstract V compute() throws Exception;

    // 在事件线程中被取消
    protected void onCompletion(V result, Throwable exception, boolean cancelled) {}

    protected void onProgress(int currrent, int max) {}

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return BackgroundTask.this.compute();
                }
            });
        }

        protected final void done() {
            GuiExecutor.instance().execute(new Runnable() {
                @Override
                public void run() {
                    V value = null;
                    Throwable thrown = null;
                    boolean cancelled = false;
                    try {
                        value = get();
                    } catch (ExecutionException e) {
                        thrown = e.getCause();
                    } catch (CancellationException e) {
                        cancelled = true;
                    } catch (InterruptedException e) {

                    } finally {
                        onCompletion(value, thrown, cancelled);
                    }
                }
            });
        }

        protected void setProgress(final int current, final int max) {
            GuiExecutor.instance().execute(new Runnable() {
                @Override
                public void run() {
                    onProgress(current, max);
                }
            });
        }
    }
}
