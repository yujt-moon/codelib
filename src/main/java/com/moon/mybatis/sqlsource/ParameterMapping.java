package com.moon.mybatis.sqlsource;

/**
 * @author yujiangtao
 * @date 2020/8/4 上午10:36
 */
public class ParameterMapping {

    private String name;

    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ParameterMapping(String name) {
        this.name = name;
    }
}
