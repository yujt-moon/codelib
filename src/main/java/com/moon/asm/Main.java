package com.moon.asm;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author yujiangtao
 * @date 2018/7/23 15:45
 */
public class Main {

    @Test
    public void testClassPrinter() {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = null;
        try {
            cr = new ClassReader("com.moon.asm.Example");
        } catch (IOException e) {
            e.printStackTrace();
        }
        cr.accept(cp, 0);
    }

    @Test
    public void testClassWriter() throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "com/moon/asm/Comparable", null, "java/lang/Object",
                new String[]{"com/moon/asm/Mesurable"});
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I",
                null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/main/java/com/moon/asm/Comparable.class"));
        fos.write(b);
        fos.flush();
        fos.close();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("com.moon.asm.Comparable", b);
        Assert.assertEquals("com.moon.asm.Comparable", c.getName());
    }
}
