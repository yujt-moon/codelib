package com.moon.mybatis.sqlsource;

import com.moon.mybatis.util.GenericTokenParser;
import com.moon.mybatis.util.ParameterMappingTokenHandler;

/**
 * 将 DynamicSqlSource 和 RawSqlSource 解析成 StaticSqlSource
 * StaticSqlSource 存储的是只有 ? 的 sql 语句以及相应的 sql 信息
 *
 * @author yujiangtao
 * @date 2020/8/7 上午11:33
 */
public class SqlSourceParser {
    public SqlSource parse(String sqlText) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = tokenParser.parse(sqlText);
        return new StaticSqlSource(sql, tokenHandler.getParameterMappings());
    }
}
