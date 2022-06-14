package com.moon.entity;

/**
 * @author yujiangtao
 * @date 2020/8/20 下午8:51
 */
public class SimpleBook {
    /**
     * 主键
     */
    private int id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    @Override
    public String toString() {
        return "SimpleBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
