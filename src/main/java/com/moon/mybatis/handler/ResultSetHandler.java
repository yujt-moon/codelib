package com.moon.mybatis.handler;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/6 上午12:26
 */
public interface ResultSetHandler {

    <T> List<T> handleResultSets(Statement stmt) throws SQLException;
}
