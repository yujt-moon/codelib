package com.moon.mybatis.sqlsource;

/**
 * 接口主要是提供功能的
 * 可以获取被 JDBC 直接执行的 Sql 语句
 *
 * @author yujiangtao
 * @date 2020/8/4 上午10:14
 */
public interface SqlSource {

    BoundSql getBoundSql(Object param);
}
