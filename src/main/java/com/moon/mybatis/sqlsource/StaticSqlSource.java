package com.moon.mybatis.sqlsource;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/7 上午11:42
 */
public class StaticSqlSource implements SqlSource {

    private String sql;

    private List<ParameterMapping> parameterMappings;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappings);
    }
}
