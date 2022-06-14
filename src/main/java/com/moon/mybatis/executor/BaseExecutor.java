package com.moon.mybatis.executor;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;
import com.moon.mybatis.transaction.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本执行器,主要处理一级缓存
 *
 * @author yujiangtao
 * @date 2020/8/6 下午7:44
 */
public abstract class BaseExecutor implements Executor {

    private Map<String, List<Object>> oneLevelCache = new HashMap<>();

    protected Configuration configuration;
    protected Transaction transaction;

    public BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        // 获取带有值的 sql 语句
        String sql = mappedStatement.getSqlSource().getBoundSql(param).getSql();
        // 从一级缓存去根据 sql 语句获取查询结果
        List<Object> result = oneLevelCache.get(sql);
        if(result != null) {
            return (List<T>) result;
        }
        // 如果没有结果,则调用相应的处理器去处理
        List<T> list = queryFromDataBase(mappedStatement, configuration, param);
        return list;
    }

    public abstract <T> List<T> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param);
}
