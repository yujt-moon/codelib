package com.moon.constant;

/**
 * 文件前缀策略枚举类型
 * @author yujiangtao
 * @date 2018/4/27 11:15
 */
public enum FilePrefixStrategy {
    DAYS(1, "日期格式的文件前缀"),
    UUID(2, "UUID格式的文件前缀");

    private int index;
    private String desc;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private FilePrefixStrategy() {}

    private FilePrefixStrategy(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }
}
