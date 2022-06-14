package com.moon.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 *
 * @author yujiangtao
 * @date 2020/9/4 下午3:33
 */
public interface Transaction extends AutoCloseable {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;
}
