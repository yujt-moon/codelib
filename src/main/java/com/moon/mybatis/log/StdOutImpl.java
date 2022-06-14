package com.moon.mybatis.log;

/**
 * @author yujiangtao
 * @date 2020/9/6 下午11:03
 */
public class StdOutImpl implements Log {

    public StdOutImpl(String name) {

    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String msg) {
        System.out.println(msg);
    }

    @Override
    public void error(String msg) {
        System.err.println(msg);
    }

    @Override
    public void error(String msg, Throwable e) {
        System.err.println(msg);
        e.printStackTrace(System.err);
    }
}
