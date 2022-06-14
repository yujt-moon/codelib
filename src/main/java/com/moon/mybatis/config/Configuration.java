package com.moon.mybatis.config;

import com.moon.mybatis.executor.CachingExecutor;
import com.moon.mybatis.executor.Executor;
import com.moon.mybatis.executor.ExecutorType;
import com.moon.mybatis.executor.SimpleExecutor;
import com.moon.mybatis.factory.MapperProxyFactory;
import com.moon.mybatis.log.Log4jImpl;
import com.moon.mybatis.log.StdOutImpl;
import com.moon.mybatis.sqlsession.SqlSessionFactory;
import com.moon.mybatis.transaction.JdbcTransactionFactory;
import com.moon.mybatis.transaction.Transaction;
import com.moon.mybatis.type.TypeAliasRegistry;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储 mybatis-config.xml 的
 *
 * @author yujiangtao
 * @date 2020/8/3 下午1:01
 */
public class Configuration {

    private Environment environment;

    private Map<String, MappedStatement> mappedStatements = new ConcurrentHashMap<>();

    private SqlSessionFactory sqlSessionFactory;

    private Map<Class<?>, Object> proxyInstances = new HashMap<>();

    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;

    protected boolean cacheEnabled = true;

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    protected Properties settings;

    protected final Set<String> loadedResources = new HashSet<>();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

        typeAliasRegistry.registerAlias("LOG4J", Log4jImpl.class);
        typeAliasRegistry.registerAlias("STDOUT_LOGGING", StdOutImpl.class);
    }

    public DataSource getDataSource() {
        return environment.getDataSource();
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setMappedStatement(String statementId, MappedStatement mappedStatement) {
        mappedStatements.put(statementId, mappedStatement);
    }

    public MappedStatement getMappedStatementById(String statementId) {
        return mappedStatements.get(statementId);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ExecutorType getDefaultExecutorType() {
        return defaultExecutorType;
    }

    public Executor newExecutor(Transaction transaction) {
        return newExecutor(transaction, defaultExecutorType);
    }

    public Executor newExecutor(Transaction transaction, ExecutorType exeType) {
        Executor executor = null;
        if(ExecutorType.BATCH == exeType) {

        } else if(ExecutorType.REUSE == exeType) {

        } else {
            executor = new SimpleExecutor(this, transaction);
        }

        // 如果开启缓存（默认开启的），则使用缓存执行器
        if(cacheEnabled) {
            executor = new CachingExecutor(executor);
        }
        return executor;
    }

    public Properties getSettings() {
        return settings;
    }

    public void setSettings(Properties settings) {
        this.settings = settings;
    }

    public void addLoadedResources(String resource) {
        this.loadedResources.add(resource);
    }

    public Set<String> getLoadedResources() {
        return this.loadedResources;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public <T> T getMapper(Class<T> type) {
        if(!proxyInstances.containsKey(type)) {
            T t = new MapperProxyFactory(this).getMapperImpl(type);
            addProxyInstance(type, t);
            return t;
        } else {
            return (T) proxyInstances.get(type);
        }
    }

    private void addProxyInstance(Class<?> type, Object proxyInstance) {
        proxyInstances.putIfAbsent(type, proxyInstance);
    }
}
