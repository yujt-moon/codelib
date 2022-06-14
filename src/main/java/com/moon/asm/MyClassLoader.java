package com.moon.asm;

/**
 * @author yujiangtao
 * @date 2018/7/23 16:17
 */
public class MyClassLoader extends ClassLoader {

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
