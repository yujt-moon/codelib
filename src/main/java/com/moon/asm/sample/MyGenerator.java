package com.moon.asm.sample;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 使用asm动态写出字节码文件
 * @author yujiangtao
 * @since 1.0
 */
public class MyGenerator {
    public static void main(String[] args) throws Exception {
        System.out.println();
        ClassWriter classWriter = new ClassWriter(0);
        // 通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_7, // java版本
                Opcodes.ACC_PUBLIC, // 类修饰符
                "Programmer", // 类的全限定名
                null, "java/lang/Object", new String[]{"java/lang/Cloneable", "java/io/Closeable"});

        // 写入属性
        classWriter.visitField(Opcodes.ACC_PRIVATE, "name", "java/lang/String", null, "haha");

        // 创建构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>", "()V", null, new String[]{"java/io/IOException"});
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 定义code方法
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "code", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("I'm a Programmer, Just Coding...");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        // 是classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File(System.getProperty("user.dir") + "/src/main/java/com/moon/asm/sample/Programmer.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
