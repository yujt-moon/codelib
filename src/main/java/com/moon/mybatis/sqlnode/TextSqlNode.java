package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;
import com.moon.mybatis.util.GenericTokenParser;
import com.moon.mybatis.util.OgnlUtils;
import com.moon.mybatis.util.SimpleTypeRegistry;
import com.moon.mybatis.util.TokenHandler;

/**
 * 包含 ${} 的 sql 片段
 *
 * @author yujiangtao
 * @date 2020/8/4 下午5:07
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    /**
     * 要处理 ${}
     * 比如 ${username} like '%${username}' 入参 username="zhangsan"
     * 处理后 username like '%zhangsan'
     *
     * @param context
     */
    @Override
    public void apply(DynamicContext context) {
        TokenHandler tokenHandler = new BindingTokenHandler(context);
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", tokenHandler);
        String parsedText = tokenParser.parse(sqlText);
        context.appendSql(parsedText);
    }

    private static class BindingTokenHandler implements TokenHandler {

        private DynamicContext context;

        public BindingTokenHandler(DynamicContext context) {
            this.context = context;
        }

        /**
         * content: 比如说 ${username}，那么 content 就是 username， username 就是 Ognl 表达式
         *
         * @param content
         * @return
         */
        @Override
        public String handleToken(String content) {
            Object paramObject = context.getBindings().get("_parameter");
            if(paramObject == null) {
                //context.getBindings().put("value", null);
                return "";
            } else if(SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            } else if(paramObject.getClass().isArray()) {
                return "";
            }

            Object value = OgnlUtils.getValue(content, paramObject);
            String strValue = value == null ? "" : String.valueOf(value);
            return strValue;
        }
    }
}
