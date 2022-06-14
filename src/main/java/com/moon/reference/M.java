package com.moon.reference;

/**
 * 参考文章：https://www.cnblogs.com/CodeBear/p/12447554.html
 *
 * @author yujiangtao
 * @date 2020/8/24 下午8:20
 */
public class M {

    // M 实例对象被垃圾收集器回收的时候调用
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
