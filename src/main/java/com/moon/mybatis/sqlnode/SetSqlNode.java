package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

/**
 * @author yujiangtao
 * @date 2020/9/1 下午4:52
 */
public class SetSqlNode implements SqlNode {

    /**
     * 下一层的节点
     */
    private SqlNode sqlNode;

    public SetSqlNode(SqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(" SET ");
        sqlNode.apply(context);
        context.deleteComma();
    }
}
