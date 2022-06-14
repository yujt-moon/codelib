package com.moon.mybatis.transaction;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author yujiangtao
 * @date 2020/9/4 下午10:05
 */
public interface TransactionFactory {

    Transaction newTransaction(Connection conn);

    Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit);
}
