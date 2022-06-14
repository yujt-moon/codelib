package com.moon.mybatis.handler;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;
import com.moon.mybatis.executor.Executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/6 上午12:37
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final Executor executor;

    private final Configuration configuration;

    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(Executor executor, Configuration configuration, MappedStatement mappedStatement) {
        this.executor = executor;
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <T> List<T> handleResultSets(Statement stmt) throws SQLException {
        return null;
    }
}
