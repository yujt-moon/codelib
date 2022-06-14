package com.moon.mybatis.log;

/**
 * 日志对象
 *
 * @author yujiangtao
 * @date 2020/8/21 下午4:06
 */
public interface Log {

    boolean isDebugEnabled();

    void debug(String msg);

    void error(String msg);

    void error(String msg, Throwable e);
}
