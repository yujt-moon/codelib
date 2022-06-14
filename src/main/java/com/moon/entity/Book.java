package com.moon.entity;

import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2020/7/17 上午11:31
 */
public class Book implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 子分类id
     */
    private String subCategoryId;

    /**
     * 作者
     */
    private Author author;

    /**
     * 状态
     */
    private String status;

    /**
     * 封面图片
     */
    private String bookCover;

    /**
     * 简介
     */
    private String intro;

    @Override
    public String toString() {
        return "Book{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", categoryId='" + categoryId + '\'' +
          ", subCategoryId='" + subCategoryId + '\'' +
          ", author='" + author + '\'' +
          ", status='" + status + '\'' +
          ", bookCover='" + bookCover + '\'' +
          ", intro='" + intro + '\'' +
          '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
