package com.moon.mybatis.sqlsession;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.executor.ExecutorType;
import com.moon.mybatis.transaction.TransactionIsolationLevel;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午3:44
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();

    SqlSession openSession(boolean autoCommit);
    SqlSession openSession(TransactionIsolationLevel level);

    SqlSession openSession(ExecutorType execType);
    SqlSession openSession(ExecutorType execType, boolean autoCommit);
    SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level);

    Configuration getConfiguration();
}
