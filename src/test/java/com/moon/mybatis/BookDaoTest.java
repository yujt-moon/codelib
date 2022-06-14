package com.moon.mybatis;


import com.moon.entity.Book;
import com.moon.entity.SimpleBook;
import com.moon.mybatis.entity.Blog;
import com.moon.mybatis.sqlsession.SqlSessionFactory;
import com.moon.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/3 下午12:29
 */
public class BookDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        // 指定全局配置文件的位置
        String resource = "mybatis-config.xml";
        // 获取指定路径的 IO 流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        // SqlSessionFactory 创建可能有几种方式，但是我还是不想要知道 SqlSessionFactory 的构造细节
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 使用手写 mybatis 框架去实现
     */
    @Test
    public void testQueryBookById() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        Book book = new Book();
        book.setId(114);
        book.setName("信息");
        Book book1 = bookDao.queryBookById(book);
        Assert.assertNotNull(book1);
    }

    @Test
    public void testQueryBookByIds() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        //int[] arr = new int[]{114, 115, 116};
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(114);
        books.add(book1);
        Book book2 = new Book();
        book2.setId(115);
        books.add(book2);
        List<SimpleBook> bookList = bookDao.queryBookByIds2(books);
        Assert.assertEquals(2, bookList.size());
    }

    @Test
    public void queryBookWithChoose() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        Book book = new Book();
        // book.setId(114);
        // book.setName("我的");
        book.setStatus("连载中");
        List<Book> bookList = bookDao.queryBookWithChoose(book);
        Assert.assertNotNull(bookList);
    }

    @Test
    public void addBook() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        Book book = new Book();
        book.setName("我自己写的");
        book.setIntro("用来搞笑的");
        int i = bookDao.addBook(book);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateBook() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        Book book = new Book();
        book.setId(1);
        book.setName("我的");
        book.setIntro("这是描述。。。");
        int i = bookDao.update(book);
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteBook() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        Book book = new Book();
        book.setId(2);
        book.setName("xxxx");
        book.setIntro("yyyy");
        bookDao.addBook(book);
        int i = bookDao.deleteBook(book);
        Assert.assertEquals(1, i);
    }

    @Test
    public void queryBlogInfo() {
        BookDao bookDao = new BookDaoImpl(sqlSessionFactory);
        List<Blog> blogs = bookDao.queryBlogInfo();
        Assert.assertNotNull(blogs);
    }
}
