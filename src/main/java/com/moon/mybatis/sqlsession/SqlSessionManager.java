package com.moon.mybatis.sqlsession;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.executor.ExecutorType;
import com.moon.mybatis.transaction.TransactionIsolationLevel;

import java.io.IOException;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/7 上午10:12
 */
public class SqlSessionManager implements SqlSession, SqlSessionFactory {

    private final SqlSessionFactory sqlSessionFactory;

    private ThreadLocal<SqlSession> localSqlSession = new ThreadLocal<>();

    private SqlSession sqlSessionProxy;

    public SqlSessionManager(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return null;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        return null;
    }

    @Override
    public int insert(String statementId, Object param) {
        return 0;
    }

    @Override
    public int update(String statementId, Object param) {
        return 0;
    }

    @Override
    public int delete(String statementId, Object param) {
        return 0;
    }

    @Override
    public SqlSession openSqlSession() {
        return null;
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
