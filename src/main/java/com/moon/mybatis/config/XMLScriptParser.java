package com.moon.mybatis.config;

import com.moon.mybatis.sqlnode.*;
import com.moon.mybatis.sqlnode.handler.NodeHandler;
import com.moon.mybatis.sqlsource.DynamicSqlSource;
import com.moon.mybatis.sqlsource.RawSqlSource;
import com.moon.mybatis.sqlsource.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对 sql 脚本进行处理
 *
 * @author yujiangtao
 * @date 2020/8/3 下午3:24
 */
public class XMLScriptParser {

    /**
     * 配置信息
     */
    private Configuration configuration;

    /**
     * 节点处理器
     */
    private Map<String, NodeHandler> nodeHandlerMap = new HashMap<>();

    /**
     * 是否包含 ${} 或者 动态标签
     */
    private boolean isDynamic = false;

    public XMLScriptParser(Configuration configuration) {
        this.configuration = configuration;
        initNodeHandlerMap();
    }

    /**
     * 初始化节点处理器
     */
    private void initNodeHandlerMap() {
        nodeHandlerMap.put("if", new IfNodeHandler());
        nodeHandlerMap.put("where", new WhereNodeHandler());
        nodeHandlerMap.put("foreach", new ForeachNodeHandler());
        nodeHandlerMap.put("choose", new ChooseNodeHandler());
        nodeHandlerMap.put("when", new WhenNodeHandler());
        nodeHandlerMap.put("otherwise", new OtherwiseNodeHandler());
        nodeHandlerMap.put("set", new SetNodeHandler());
    }

    /**
     * 解析脚本节点
     * @param selectEle
     * @return
     */
    public SqlSource parseScriptNode(Element selectEle) {
        // 首先先将 sql 脚本按照不同的类型，封装到不同的 SqlNode
        SqlNode rootSqlNode = parseDynamicTags(selectEle);
        // 再将 SqlNode 集合封装到 SqlSource 中
        // 由于带有 #{} 和 ${}，动态标签 的 sql 处理方式不同，所以需要封装到不同的 SqlSource 中
        SqlSource sqlSource = null;
        if(isDynamic) {
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }

    private SqlNode parseDynamicTags(Element element) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        int nodeCount = element.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = element.node(i);
            // 需要去区分 select 标签的子节点类型
            // 如果是文本类型则封装到 TextSqlNode 或者 StaticTextSqlNode
            if(node instanceof Text) {
                String sqlText = node.getText().trim();
                if(sqlText == null || sqlText.equals("")) {
                    continue;
                }
                // 判断文本中是否带有 ${}
                if(sqlText.indexOf("${") > -1) {
                    sqlNodes.add(new TextSqlNode(sqlText));
                    isDynamic = true;
                } else {
                    sqlNodes.add(new StaticTextSqlNode(sqlText));
                }

            } else if(node instanceof Element) { // 则递归解析
                // 比如 if|where|foreach 等动态 sql 子标签需要在此处处理
                // 根据标签名称，封装到不同的节点信息
                String nodeName = node.getName().toLowerCase();
                // 获取对应的 NodeHandler
                NodeHandler nodeHandler = nodeHandlerMap.get(nodeName);
                if(nodeHandler == null) {
                    throw new RuntimeException("不支持对该<" + nodeName + ">节点的处理!");
                }
                nodeHandler.handleNode((Element) node, sqlNodes);
                isDynamic = true;
            }
        }
        // 如果当前只有一个 sqlNode, 并且 sqlNode 为文本
        if(sqlNodes.size() == 1 && element.node(0) instanceof Text) {
            return sqlNodes.get(0);
        }
        return new MixedSqlNode(sqlNodes);
    }

    /**
     * 对 <if></if> 标签进行处理
     */
    private class IfNodeHandler implements NodeHandler {
        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            // 对 if 标签进行解析
            SqlNode mixedSqlNode = parseDynamicTags(nodeToHandle);
            String test = nodeToHandle.attributeValue("test");
            IfSqlNode ifSqlNode = new IfSqlNode(test, mixedSqlNode);
            targetContexts.add(ifSqlNode);
        }
    }

    /**
     * 对 <where></where> 标签进行处理
     */
    private class WhereNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            // 对 where 标签进行解析
            SqlNode mixedSqlNode = parseDynamicTags(nodeToHandle);
            WhereSqlNode whereSqlNode = new WhereSqlNode(mixedSqlNode);
            targetContexts.add(whereSqlNode);
        }
    }

    /**
     * 对 <foreach></foreach> 标签进行处理
     */
    private class ForeachNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            // 对 foreach 标签进行解析
            SqlNode mixedSqlNode = parseDynamicTags(nodeToHandle);

            String collection = nodeToHandle.attribute("collection").getValue();
            String item = nodeToHandle.attribute("item").getValue();
            String index = nodeToHandle.attribute("index").getValue();
            String separator = nodeToHandle.attribute("separator").getValue();
            String open = nodeToHandle.attribute("open").getValue();
            String close = nodeToHandle.attribute("close").getValue();

            ForeachSqlNode foreachSqlNode = new ForeachSqlNode.Builder()
                    .collection(collection)
                    .item(item)
                    .index(index)
                    .separator(separator)
                    .open(open)
                    .close(close)
                    .sqlNode(mixedSqlNode)
                    .build();

            targetContexts.add(foreachSqlNode);
        }
    }

    /**
     * 对 <choose><when></when><otherwise></otherwise></choose> 标签进行处理
     */
    private class ChooseNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            SqlNode sqlNode = parseDynamicTags(nodeToHandle);
            ChooseSqlNode chooseSqlNode = new ChooseSqlNode(sqlNode);
            targetContexts.add(chooseSqlNode);
        }
    }

    private class WhenNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            SqlNode sqlNode = parseDynamicTags(nodeToHandle);
            String test = nodeToHandle.attributeValue("test");
            WhenSqlNode whenSqlNode = new WhenSqlNode(test, sqlNode);
            targetContexts.add(whenSqlNode);
        }
    }

    private class OtherwiseNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            SqlNode sqlNode = parseDynamicTags(nodeToHandle);
            OtherwiseSqlNode otherwiseSqlNode = new OtherwiseSqlNode(sqlNode);
            targetContexts.add(otherwiseSqlNode);
        }
    }

    private class SetNodeHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContexts) {
            SqlNode sqlNode = parseDynamicTags(nodeToHandle);
            SetSqlNode setSqlNode = new SetSqlNode(sqlNode);
            targetContexts.add(setSqlNode);
        }
    }
}
