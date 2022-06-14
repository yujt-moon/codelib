package com.moon.mybatis.sqlsource;

import com.moon.mybatis.sqlnode.SqlNode;

/**
 * 专门封装和处理带有 ${} 和动态 sql 标签
 *
 * @author yujiangtao
 * @date 2020/8/4 下午4:44
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // 首先先调用 sqlNode 的处理,将动态标签和 ${} 处理一下
        DynamicContext context = new DynamicContext(param);
        // 根据对应的 SqlNode 的调用对应的方法
        rootSqlNode.apply(context);

        // 再调用 sqlSourceParser 来处理 #{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql());
        return sqlSource.getBoundSql(param);
    }
}
