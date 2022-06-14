package com.moon.mybatis;

import com.moon.entity.Book;
import com.moon.entity.SimpleBook;
import com.moon.mybatis.entity.Blog;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午3:29
 */
public interface BookDao {

    Book queryBookById(Book param);

    List<Book> queryBookByIds(int[] arr);

    List<SimpleBook> queryBookByIds2(List<Book> books);

    List<Book> queryBookWithChoose(Book param);

    int addBook(Book param);

    int insertBookWithId(Book param);

    int update(Book param);

    int deleteBook(Book param);

    List<Blog> queryBlogInfo();
}
