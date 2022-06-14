package com.moon.mybatis.entity;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/9/9 下午9:14
 */
public class Blog {

    private int id;

    private String title;

    private Author author;

    private List<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
