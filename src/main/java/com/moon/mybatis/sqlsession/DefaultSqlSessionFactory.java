package com.moon.mybatis.sqlsession;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.Environment;
import com.moon.mybatis.executor.Executor;
import com.moon.mybatis.executor.ExecutorType;
import com.moon.mybatis.transaction.Transaction;
import com.moon.mybatis.transaction.TransactionFactory;
import com.moon.mybatis.transaction.TransactionIsolationLevel;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午3:45
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), level, false);
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        return openSessionFromDataSource(execType, null, false);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return openSessionFromDataSource(execType, null, autoCommit);
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return openSessionFromDataSource(execType, level, false);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        final Environment environment = configuration.getEnvironment();
        final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
        Transaction transaction = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
        final Executor executor = configuration.newExecutor(transaction, execType);
        return new DefaultSqlSession(configuration, executor, autoCommit);
    }

    private TransactionFactory getTransactionFactoryFromEnvironment(Environment environment) {
        return environment.getTransactionFactory();
    }
}
