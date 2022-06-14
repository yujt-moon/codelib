package com.moon.mybatis.mapperproxy;

import com.moon.entity.Book;
import com.moon.entity.SimpleBook;
import com.moon.mybatis.BookDao;
import com.moon.mybatis.BookDaoImpl;
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
 * @date 2020/12/25 下午4:41
 */
public class MapperProxyTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        // 保存生成的代理类的字节码文件（必须在main方法中使用，如果使用junit，添加jvm参数 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true）
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 指定全局配置文件的位置
        String resource = "mybatis-config.xml";
        // 获取指定路径的 IO 流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        // SqlSessionFactory 创建可能有几种方式，但是我还是不想要知道 SqlSessionFactory 的构造细节
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 动态生成代理类
     */
    @Test
    public void testQueryBookById() {
        BookMapper bookMapper = sqlSessionFactory.getConfiguration().getMapper(BookMapper.class);
        Book book = new Book();
        book.setId(114);
        book.setName("信息");
        Book book1 = bookMapper.queryBookById(book);
        Assert.assertNotNull(book1);
    }

    @Test
    public void testAddBook() {
        BookMapper bookMapper = sqlSessionFactory.getConfiguration().getMapper(BookMapper.class);
        Book book = new Book();
        book.setName("111");
        book.setIntro("222");
        int i = bookMapper.addBook(book);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateBook() {
        BookMapper bookMapper = sqlSessionFactory.getConfiguration().getMapper(BookMapper.class);
        Book book = new Book();
        book.setId(1);
        book.setName("我的x");
        book.setIntro("这是描述。。。");
        int i = bookMapper.update(book);
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteBook() {
        BookMapper bookMapper = sqlSessionFactory.getConfiguration().getMapper(BookMapper.class);
        Book book = new Book();
        book.setId(2);
        book.setName("xxxx");
        book.setIntro("yyyy");
        bookMapper.addBook(book);
        int i = bookMapper.deleteBook(book);
        Assert.assertEquals(1, i);
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
}
