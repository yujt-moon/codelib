package com.moon.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yujiangtao
 * @date 2019/2/17 13:37
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
                new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                    @Override
                    public ProductInfo call() throws DataLoadException {
                        return null;
                    }
                });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if(cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw ExceptionUtils.launderThrowable(cause);
            }
        }
    }
}

class ProductInfo {

}

class DataLoadException extends Exception {

}
