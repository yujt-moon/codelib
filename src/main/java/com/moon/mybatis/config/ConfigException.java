package com.moon.mybatis.config;

/**
 * @author yujiangtao
 * @date 2020/9/5 下午2:53
 */
public class ConfigException extends RuntimeException {

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
