package com.moon.mybatis.sqlnode.handler;

import com.moon.mybatis.sqlnode.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * 针对不同子标签进行处理，处理之后，封装到对应的 SqlNode 对象中
 * 比如 if 标签被处理之后，会封装到 IfSqlNode 对象中
 *
 * @author yujiangtao
 * @date 2020/8/4 下午5:31
 */
public interface NodeHandler {

    void handleNode(Element nodeToHandle, List<SqlNode> targetContexts);
}
