package com.moon.mybatis;

import com.moon.entity.Book;
import com.moon.entity.SimpleBook;
import com.moon.mybatis.entity.Blog;
import com.moon.mybatis.sqlsession.SqlSession;
import com.moon.mybatis.sqlsession.SqlSessionFactory;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2020/8/6 下午3:37
 */
public class BookDaoImpl implements BookDao {

    // 等待注入
    private SqlSessionFactory sqlSessionFactory;

    public BookDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Book queryBookById(Book param) {
        // sqlSession 被调用次数，而且它需要 Configuration 对象
        // 可以考虑使用工厂来屏蔽 SqlSession 的构造细节
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        Book book = sqlSession.selectOne("com.moon.mybatis.BookDao.queryBookById", param);
        return book;
    }

    @Override
    public List<Book> queryBookByIds(int[] arr) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        List<Book> books = sqlSession.selectList("com.moon.mybatis.BookDao.queryBookByIds", arr);
        return books;
    }

    @Override
    public List<SimpleBook> queryBookByIds2(List<Book> books) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        List<SimpleBook> bookList = sqlSession.selectList("com.moon.mybatis.BookDao.queryBookByIds2", books);
        return bookList;
    }

    @Override
    public List<Book> queryBookWithChoose(Book param) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        List<Book> bookList = sqlSession.selectList("com.moon.mybatis.BookDao.queryBookWithChoose", param);
        return bookList;
    }

    @Override
    public int addBook(Book param) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        int rows = sqlSession.insert("com.moon.mybatis.BookDao.addBook", param);
        return rows;
    }

    @Override
    public int insertBookWithId(Book param) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        int rows = sqlSession.insert("com.moon.mybatis.BookDao.insertBookWithId", param);
        return rows;
    }

    @Override
    public int update(Book param) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        int rows = sqlSession.update("com.moon.mybatis.BookDao.update", param);
        return rows;
    }

    @Override
    public int deleteBook(Book param) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        int rows = sqlSession.update("com.moon.mybatis.BookDao.deleteBook", param);
        return rows;
    }

    @Override
    public List<Blog> queryBlogInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        List<Blog> list = sqlSession.selectList("com.moon.mybatis.BookDao.queryBlogInfo", null);
        return list;
    }


}
