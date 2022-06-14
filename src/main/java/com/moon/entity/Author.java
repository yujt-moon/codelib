package com.moon.entity;

/**
 * @author yujiangtao
 * @date 2020/8/5 上午10:53
 */
public class Author {

    private int authorId;

    private String authorName;

    private String realName;

    private static String content = "content";

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }

    private void printAuthorInfo(String name) {
        System.out.println("Author{" +
                "authorId=" + authorId +
                ", authorName='" + name + '\'' +
                ", realName='" + realName + '\'' +
                '}');
    }

    public static int getInputLength(String str) {
        return str.length();
    }

    public static String getContent() {
        return content;
    }
}
