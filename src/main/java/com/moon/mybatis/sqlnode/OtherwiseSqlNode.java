package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

/**
 * @author yujiangtao
 * @date 2020/8/25 下午3:53
 */
public class OtherwiseSqlNode implements SqlNode {

    private SqlNode sqlNode;

    public OtherwiseSqlNode(SqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        sqlNode.apply(context);
    }
}
