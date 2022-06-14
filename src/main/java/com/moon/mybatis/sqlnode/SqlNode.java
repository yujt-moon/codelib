package com.moon.mybatis.sqlnode;

import com.moon.mybatis.sqlsource.DynamicContext;

import java.io.Serializable;

/**
 * 提供对 sql 脚本的解析
 *
 * @author yujiangtao
 * @date 2020/8/4 下午3:18
 */
public interface SqlNode extends Serializable {

    /**
     * 解析 SqlNode 节点
     * @param context
     */
    void apply(DynamicContext context);
}
