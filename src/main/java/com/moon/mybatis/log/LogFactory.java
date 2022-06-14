package com.moon.mybatis.log;

import java.lang.reflect.Constructor;

/**
 * @author yujiangtao
 * @date 2020/8/24 下午2:52
 */
public final class LogFactory {

    private static Constructor<? extends Log> logConstructor;

    static {
        tryImplementation(LogFactory::useLog4JLogging);
        tryImplementation(LogFactory::useCustomLogging);
    }

    private LogFactory() {}

    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String name) {
        try {
            return logConstructor.newInstance(name);
        } catch (Throwable t) {
            throw new RuntimeException("Error creating logger for logger " + name + ". Cause: " + t, t);
        }
    }

    public static synchronized void useLog4JLogging() {
        setImplementation(com.moon.mybatis.log.Log4jImpl.class);
    }

    public static synchronized void useCustomLogging() {
        setImplementation(com.moon.mybatis.log.StdOutImpl.class);
    }

    /**
     * 尝试加载可以使用的 Log 的实现
     * @param runnable
     */
    private static void tryImplementation(Runnable runnable) {
        if(logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable t) {
                // ignore
            }
        }
    }

    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            Constructor<? extends Log> candidate = implClass.getConstructor(String.class);
            Log log = candidate.newInstance(LogFactory.class.getName());
            if(log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new RuntimeException("Error setting Log implementation. Cause: " + t, t);
        }
    }
}
