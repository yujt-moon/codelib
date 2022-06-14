package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;
import com.moon.mybatis.util.OgnlUtils;

/**
 * 表示 <if></if> 标签所代表的节点
 *
 * @author yujiangtao
 * @date 2020/8/4 下午5:48
 */
public class IfSqlNode implements SqlNode {

    /**
     * 布尔表达式
     */
    private String test;

    /**
     * 下一层 SqlNode 节点
     */
    private SqlNode sqlNode;

    public IfSqlNode(String test, SqlNode sqlNode) {
        this.test = test;
        this.sqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        // 使用 Ognl 的 api 对 test 标签属性中的布尔表达式进行处理，获取布尔值
        boolean evaluteBoolean = OgnlUtils.evaluteBoolean(test, context.getBindings().get("_parameter"));
        // 如果 test 标签属性中的表达式判断为 true，才进行子节点的处理
        if(evaluteBoolean) {
            sqlNode.apply(context);
        }
    }
}
