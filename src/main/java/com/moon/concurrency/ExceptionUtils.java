package com.moon.concurrency;

/**
 * 异常工具类
 * @author yujiangtao
 * @date 2019/2/17 15:29
 */
public class ExceptionUtils {


    public static RuntimeException launderThrowable(Throwable t) {
        if(t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if(t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
