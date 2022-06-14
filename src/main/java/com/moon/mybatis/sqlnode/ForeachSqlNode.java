package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;
import com.moon.mybatis.util.SimpleTypeRegistry;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/**
 * <foreach></foreach> 节点
 * 建造者模式创建,参数更加清晰
 *
 * @author yujiangtao
 * @date 2020/8/9 下午6:18
 */
public class ForeachSqlNode implements SqlNode {

    private String collection;

    private String item;

    private String index;

    private String separator;

    private String open;

    private String close;

    private SqlNode sqlNode;

    @Override
    public void apply(DynamicContext context) {
        // 获取参数
        Object param = context.getBindings().get("_parameter");
        applyOpen(context);

        // 判断当前对象是否是数组
        if("array".equals(collection) && param.getClass().isArray()) {
            int size = Array.getLength(param);
            for (int i = 0; i < size; i++) {
                if(sqlNode instanceof StaticTextSqlNode) {
                    // context.appendSql("[" + i + "]");
                } else {
                    context.appendSql("'" + Array.get(param, i) + "'");
                }
                if(separator != null && !"".equals(separator) && i != (size - 1)) {
                    context.appendSql(separator + " ");
                }
            }
        } else if("list".equals(collection)) {
            Collection c = (Collection)param;
            int i = 0;
            Iterator iterator = c.iterator();
            while(iterator.hasNext()) {
                Object next = iterator.next();
                if(sqlNode instanceof StaticTextSqlNode) {
                    if(SimpleTypeRegistry.isSimpleType(next.getClass())) {

                    } else {
                        context.appendSql(((StaticTextSqlNode) sqlNode).getSqlText().replaceAll(item + ".",
                                "[" + i +"]."));
                    }
                } else if(sqlNode instanceof TextSqlNode) {
                    // 克隆当前 sqlNode 使得每次的文本内容都是最新的
                    TextSqlNode textSqlNode = (TextSqlNode) SerializationUtils.clone(sqlNode);
                    textSqlNode.setSqlText(textSqlNode.getSqlText().replaceAll(item + ".",
                            "[" + i +"]."));
                    textSqlNode.apply(context);
                } else {
                    sqlNode.apply(context);
                }
                if(separator != null && !"".equals(separator) && i != (c.size() - 1)) {
                    context.appendSql(separator + " ");
                }
                i++;
            }
        } else {
            throw new RuntimeException("不支持的 collection 类型:" + collection);
        }
        applyClose(context);
    }

    /**
     * 处理 open
     * @param context
     */
    private void applyOpen(DynamicContext context) {
        if(open != null && !"".equals(open)) {
            context.appendSql(" " + open + " ");
        }
    }

    /**
     * 处理 close
     * @param context
     */
    private void applyClose(DynamicContext context) {
        if(close != null && !"".equals(close)) {
            context.appendSql(close + " ");
        }
    }

    private ForeachSqlNode() {
        throw new RuntimeException("不支持直接创建 ForeachSqlNode!");
    }

    public ForeachSqlNode(Builder builder) {
        collection = builder.collection;
        item = builder.item;
        index = builder.index;
        separator = builder.separator;
        open = builder.open;
        close = builder.close;
        sqlNode = builder.sqlNode;
    }

    public static final class Builder {
        private String collection;

        private String item;

        private String index;

        private String separator;

        private String open;

        private String close;

        private SqlNode sqlNode;

        public Builder collection(String collection) {
            this.collection = collection;
            return this;
        }

        public Builder item(String item) {
            this.item = item;
            return this;
        }

        public Builder index(String index) {
            this.index = index;
            return this;
        }

        public Builder separator(String separator) {
            this.separator = separator;
            return this;
        }

        public Builder open(String open) {
            this.open = open;
            return this;
        }

        public Builder close(String close) {
            this.close = close;
            return this;
        }

        public Builder sqlNode(SqlNode sqlNode) {
            this.sqlNode = sqlNode;
            return this;
        }

        public ForeachSqlNode build() {
            return new ForeachSqlNode(this);
        }
    }
}
