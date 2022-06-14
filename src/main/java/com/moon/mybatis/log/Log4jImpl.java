package com.moon.mybatis.log;

import org.apache.log4j.Logger;

/**
 * Log4j 的实现版本
 *
 * @author yujiangtao
 * @date 2020/8/21 下午4:07
 */
public class Log4jImpl implements Log {

    protected final Logger logger;

    public Log4jImpl(String clazz) {
        this.logger = Logger.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String msg, Throwable e) {
        logger.error(msg, e);
    }
}
