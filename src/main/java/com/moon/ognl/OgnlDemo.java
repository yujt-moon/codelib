package com.moon.ognl;

import com.moon.entity.Author;
import com.moon.entity.Book;
import ognl.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * OgnlContext 用法
 * 1. 使用 Ognl 表达式语言取值，如果取非根元素的值，必须用#号
 * 2. 使用 Ognl 表达式语言取值，如果取根元素的值，不用#号
 * 3. Ognl 可以调用静态方法
 *
 * @author yujiangtao
 * @date 2020/7/31 下午4:29
 */
public class OgnlDemo {

    // 非根元素
    @Test
    public void testOgnl() throws OgnlException {
        // 创建一个 Ognl 上下文对象
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(this,
                new DefaultMemberAccess(true),
                new DefaultClassResolver(),
                new DefaultTypeConverter());

        Book book = new Book();
        book.setName("我欲封天");
        //context.setRoot(book);
        Author author = new Author();
        author.setAuthorName("耳根");
        author.setRealName("刘勇");
        book.setAuthor(author);
        context.put("book", book);
        context.put("author", author);

        Map<String, String> map = new HashMap<>();
        map.put("name", "lisi");
        context.setRoot(map);
        String name = (String) Ognl.getValue(Ognl.parseExpression("name"), context, context.getRoot());


        String bookName = (String) Ognl.getValue(Ognl.parseExpression("#book.name"), context, context.getRoot());
        System.out.printf("书名：%s\n", bookName);

        String realName = (String) Ognl.getValue(Ognl.parseExpression("#author.realName"), context, context.getRoot());
        System.out.printf("真名： %s\n", realName);

        String authorName = (String) Ognl.getValue(Ognl.parseExpression("#book.author.authorName"), context, context.getRoot());
        System.out.printf("笔名： %s\n", authorName);

        // 实例方法调用
        String toString = (String) Ognl.getValue(Ognl.parseExpression("#book.toString()"), context, context.getRoot());
        System.out.println(toString);

        // 带参数实例方法调用
        Ognl.getValue(Ognl.parseExpression("#book.author.printAuthorInfo(\"哈哈\")"), context, context.getRoot());
        String info = "呵呵";
//        Ognl.getValue(Ognl.parseExpression("#book.author.printAuthorInfo(#{info: info})"), context, context.getRoot());

        // 静态方法调用
        int length = (int) Ognl.getValue(Ognl.parseExpression("@com.moon.entity.Author@getInputLength(\"哈哈\")"), context, context.getRoot());
        System.out.printf("长度： %d\n", length);

        // 获取静态变量
        String content = (String) Ognl.getValue(Ognl.parseExpression("@com.moon.entity.Author@content"), context, context.getRoot());
        System.out.printf("content: %s\n", content);
    }

    @Test
    public void testArrOgnl() throws OgnlException {
        // 创建一个 Ognl 上下文对象
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(this,
                new DefaultMemberAccess(true),
                new DefaultClassResolver(),
                new DefaultTypeConverter());

        context.setRoot(new int[]{1, 2, 3});

        System.out.println(Ognl.getValue(Ognl.parseExpression("[0]"), context, context.getRoot()));

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(114);
        books.add(book1);
        Book book2 = new Book();
        book2.setId(115);
        books.add(book2);

        context.setRoot(books);
        System.out.println(Ognl.getValue(Ognl.parseExpression("[0].id"), context, context.getRoot()));
    }

    @Test
    public void testEmptyString() throws OgnlException {
        // 创建一个 Ognl 上下文对象
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(this,
                new DefaultMemberAccess(true),
                new DefaultClassResolver(),
                new DefaultTypeConverter());
        String emptyStr = "";
        context.put("emptyStr", emptyStr);
        boolean isTrue = (Boolean) Ognl.getValue("#emptyStr == 0", context, context.getRoot());
        System.out.println(isTrue);
    }
}
