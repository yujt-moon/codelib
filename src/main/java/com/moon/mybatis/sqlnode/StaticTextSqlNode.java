package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

/**
 * 仅包含 #{}
 *
 * @author yujiangtao
 * @date 2020/8/4 下午5:16
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        // Sql 文本追加
        context.appendSql(sqlText);
    }

    public String getSqlText() {
        return sqlText;
    }
}
