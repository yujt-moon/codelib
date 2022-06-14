package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;
import com.moon.mybatis.util.OgnlUtils;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/25 下午3:31
 */
public class ChooseSqlNode implements SqlNode {

    /**
     * 存放 <when> <otherwise> 节点
     */
    private SqlNode sqlNode;

    public ChooseSqlNode(SqlNode sqlNode) {
        this.sqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        if(sqlNode != null && sqlNode instanceof MixedSqlNode) {
            Object parameter = context.getBindings().get("_parameter");
            List<SqlNode> sqlNodes = ((MixedSqlNode) sqlNode).getSqlNodes();
            for (SqlNode node : sqlNodes) {
                if(node instanceof WhenSqlNode) {
                    WhenSqlNode whenSqlNode = ((WhenSqlNode) node);
                    boolean result = OgnlUtils.evaluteBoolean(whenSqlNode.getTest(), parameter);
                    if(result) {
                        whenSqlNode.apply(context);
                        break;
                    }
                } else if(node instanceof OtherwiseSqlNode) {
                    OtherwiseSqlNode otherwiseSqlNode = (OtherwiseSqlNode) node;
                    otherwiseSqlNode.apply(context);
                    break;
                }
            }
        }
    }
}
