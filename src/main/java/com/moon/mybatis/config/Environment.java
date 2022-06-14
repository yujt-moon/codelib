package com.moon.mybatis.config;

import com.moon.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;

/**
 * @author yujiangtao
 * @date 2020/9/5 下午2:11
 */
public class Environment {

    private String id;

    private TransactionFactory transactionFactory;

    private DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        if(id == null) {
            throw new IllegalArgumentException("Parameter 'id' must not be null.");
        }
        if(transactionFactory == null) {
            throw new IllegalArgumentException("Parameter 'transactionFactory' must not be null.");
        }
        if(dataSource == null) {
            throw new IllegalArgumentException("Parameter 'dataSource' must not be null.");
        }
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public String getId() {
        return id;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static class Builder {
        private String id;

        private TransactionFactory transactionFactory;

        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(this.id, this.transactionFactory, this.dataSource);
        }
    }
}
