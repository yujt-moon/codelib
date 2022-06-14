package com.moon.mybatis.sqlsession;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;
import com.moon.mybatis.executor.Executor;

import java.io.IOException;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午3:47
 */
public class DefaultSqlSession implements SqlSession {

    // 等待注入
    private final Configuration configuration;

    private final Executor executor;

    private final boolean autoCommit;

    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.autoCommit = autoCommit;
    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this(configuration, executor, false);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type);
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if(list == null || list.size() == 0) {
            return null;
        } else if(list.size() == 1){
            return (T) list.get(0);
        }
        throw new RuntimeException("expect one result, but get " + list.size() + " results!");
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        // 根据 statementId 获取 MappedStatement 对象
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        // 执行 Statement 的操作(执行方式有多种,一种是带有二级缓存的执行方式,
        // 一种是基本执行方式(只带有一级缓存,基本执行方式又分成几种:基本执行器、批处理执行器))
        // 此处可以考虑放到 MappedStatement 对象中,该对象是否配置了二级缓存来确定创建的是哪个 Executor
        return executor.query(mappedStatement, configuration, param);
    }

    @Override
    public int insert(String statementId, Object param) {
        // 根据 statementId 获取 MappedStatement 对象
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        return executor.doUpdate(mappedStatement, configuration, param);
    }

    @Override
    public int update(String statementId, Object param) {
        // 根据 statementId 获取 MappedStatement 对象
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        return executor.doUpdate(mappedStatement, configuration, param);
    }

    @Override
    public int delete(String statementId, Object param) {
        // 根据 statementId 获取 MappedStatement 对象
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        return executor.doUpdate(mappedStatement, configuration, param);
    }

    @Override
    public void close() throws IOException {

    }
}
