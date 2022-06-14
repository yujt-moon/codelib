package com.moon.asm;

import org.objectweb.asm.ClassWriter;

/**
 * @author yujiangtao
 * @date 2020/8/29 上午11:08
 */
public class StubClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(name.endsWith("_Stub")) {
            ClassWriter cw = new ClassWriter(0);
            // ...
            byte[] b = cw.toByteArray();
            return defineClass(name, b, 0, b.length);
        }
        return super.findClass(name);
    }
}
