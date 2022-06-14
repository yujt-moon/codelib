package com.moon.mybatis.executor;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;

import java.util.List;

/**
 * 处理二级缓存
 *
 * @author yujiangtao
 * @date 2020/8/6 下午7:41
 */
public class CachingExecutor implements Executor {

    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {

        // 从二级缓存中根据 sql 语句获取处理结果(二级缓存怎么存)
        // 如果有直接返回,如果没有则继续委托给基本执行器去执行
        return delegate.query(mappedStatement, configuration, param);
    }

    @Override
    public int doUpdate(MappedStatement mappedStatement, Configuration configuration, Object param) {
        return delegate.doUpdate(mappedStatement, configuration, param);
    }
}
