package com.moon.mybatis.factory;

import com.moon.mybatis.config.Configuration;

import java.lang.reflect.Proxy;

/**
 * https://www.pianshen.com/article/493917623/
 *
 * @author yujiangtao
 * @date 2020/12/24 下午9:06
 */
public class MapperProxyFactory {

    private Configuration configuration;

    public MapperProxyFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapperImpl(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy<T>(configuration));
    }
}
