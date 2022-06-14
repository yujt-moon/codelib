package com.moon.mybatis.executor;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午7:37
 */
public interface Executor {

    /**
     * 查询执行器
     * @param <T>
     * @param mappedStatement 获取 sql 语句和入参出参等信息
     * @param configuration 获取数据源对象
     * @param param 入参对象
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);

    /**
     * 更新执行器
     * @param mappedStatement
     * @param configuration
     * @param param
     * @return
     */
    int doUpdate(MappedStatement mappedStatement, Configuration configuration, Object param);
}
