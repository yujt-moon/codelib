package com.moon.mybatis.sqlsession;

import java.io.Closeable;
import java.util.List;

/**
 * 表示一个 sql 会话，就是一次 CRUD 操作
 *
 * @author yujiangtao
 * @date 2020/8/6 下午3:34
 */
public interface SqlSession extends Closeable {

    <T> T getMapper(Class<T> type);

    <T> T selectOne(String statementId, Object param);

    <T> List<T> selectList(String statementId, Object param);

    int insert(String statementId, Object param);

    int update(String statementId, Object param);

    int delete(String statementId, Object param);
}
