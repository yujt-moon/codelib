package com.moon.mybatis.sqlsource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态上下文
 * 存储 SqlNode 解析过程中产生的 sql 片段，并完成字符串拼接
 * 存储 SqlNode 解析过程中需要的入参信息
 *
 * @author yujiangtao
 * @date 2020/8/4 下午3:21
 */
public class DynamicContext {

    /**
     * 存储字符串形式的 sql
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * 存储 sql 查询入参
     */
    private Map<String, Object> initBindings = new HashMap<>();

    public DynamicContext(Object param) {
        initBindings.put("_parameter", param);
    }

    public void appendSql(String sql) {
        sb.append(sql);
        sb.append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return initBindings;
    }

    public void deleteComma() {
        // 找到最后一个逗号
        int commaIndex = sb.lastIndexOf(",");
        if(commaIndex == -1) {
            return;
        }
        boolean isAllBlank = true;
        for(int i = commaIndex + 1; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(c != ' ' && c != '\t' && c != '\n') {
                isAllBlank = false;
                break;
            }
        }
        if(isAllBlank) {
            sb.deleteCharAt(commaIndex);
        }
    }
}
