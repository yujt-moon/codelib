package com.moon.mybatis.mapperproxy;

import com.moon.entity.Book;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/12/25 下午4:44
 */
public interface BookMapper {
    Book queryBookById(Book param);

    int addBook(Book param);

    int update(Book param);

    int deleteBook(Book param);

    List<Book> queryBookByIds(int[] arr);
}
