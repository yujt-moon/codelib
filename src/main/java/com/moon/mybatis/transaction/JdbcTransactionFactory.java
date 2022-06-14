package com.moon.mybatis.transaction;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author yujiangtao
 * @date 2020/9/4 下午10:07
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(ds, level, autoCommit);
    }
}
