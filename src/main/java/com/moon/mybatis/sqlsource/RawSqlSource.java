package com.moon.mybatis.sqlsource;

import com.moon.mybatis.sqlnode.SqlNode;

/**
 * @author yujiangtao
 * @date 2020/8/4 下午4:48
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(null);
        rootSqlNode.apply(context);
        // 在这里要先对 sql 节点进行解析
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        sqlSource = sqlSourceParser.parse(context.getSql());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // 从 StaticSqlSource 中获取相应信息
        return sqlSource.getBoundSql(param);
    }
}
