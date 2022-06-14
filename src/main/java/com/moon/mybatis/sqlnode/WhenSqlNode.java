package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

/**
 * @author yujiangtao
 * @date 2020/8/25 下午3:49
 */
public class WhenSqlNode implements SqlNode {

    /**
     * 测试条件
     */
    private String test;

    /**
     * 存储 when 标签下的节点
     */
    private SqlNode sqlNode;

    public WhenSqlNode(String test, SqlNode sqlNode) {
        this.test = test;
        this.sqlNode = sqlNode;
    }

    public String getTest() {
        return test;
    }

    @Override
    public void apply(DynamicContext context) {
        sqlNode.apply(context);
    }
}
