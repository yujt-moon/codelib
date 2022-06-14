package com.moon.asm.sample;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * javassist生成Java字节码
 * @author yujaingtao
 * @since 1.0
 */
public class MyGenerator2 {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 创建Programmer类
        CtClass cc = pool.makeClass("com.samples.Programmer");
        // 定义code方法
        CtMethod method = CtMethod.make("public void code() {}", cc);
        // 插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer, Just Coding...\");");
        cc.addMethod(method);
        // 保存生成的字节码
        cc.writeFile("C://test");
    }
}
