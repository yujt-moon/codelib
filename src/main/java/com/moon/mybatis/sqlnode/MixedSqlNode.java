package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含所有类型的 SqlNode
 *
 * @author yujiangtao
 * @date 2020/8/4 下午4:36
 */
public class MixedSqlNode implements SqlNode {

    /**
     * 存储某一层次的所有节点
     */
    private List<SqlNode> sqlNodes = new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    public List<SqlNode> getSqlNodes() {
        return sqlNodes;
    }

    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }
}
