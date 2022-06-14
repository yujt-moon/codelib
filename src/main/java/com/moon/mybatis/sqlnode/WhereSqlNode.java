package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

/**
 * <where></where> 标签
 *
 * @author yujiangtao
 * @date 2020/8/9 下午5:03
 */
public class WhereSqlNode implements SqlNode {

    /**
     * 下一层的节点
     */
    private SqlNode sqlNode;

    public WhereSqlNode(SqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        // 拼接固定 sql
        context.appendSql(" WHERE 1=1 ");
        // 下层节点递归处理
        sqlNode.apply(context);
    }
}
