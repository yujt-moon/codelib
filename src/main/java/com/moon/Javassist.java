package com.moon;

import javassist.*;

import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2021/8/10 下午1:48
 */
public class Javassist {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass intf = pool.get(Serializable.class.getName());
        // 创建实现类
        CtClass ctClass = pool.makeClass("com.moon.XxxImpl");


        // 导入包
        pool.importPackage("java.util");

        // 添加接口
        ctClass.addInterface(intf);
        // 添加属性
        CtField serviceVersionField = new CtField(pool.get("java.lang.String"), "serviceVersion", ctClass);
        serviceVersionField.setModifiers(Modifier.PRIVATE | Modifier.FINAL);
        ctClass.addField(serviceVersionField);
        CtField timeoutField = new CtField(pool.get("long"), "timeout", ctClass);
        timeoutField.setModifiers(Modifier.PRIVATE | Modifier.FINAL);
        ctClass.addField(timeoutField);
        CtField nameField = CtField.make("private String name;", ctClass);
        ctClass.addField(nameField);

        // 设置相应的 setter 和 getter 方法
        ctClass.addMethod(CtNewMethod.getter("getServiceVersion", serviceVersionField));
        ctClass.addMethod(CtNewMethod.setter("setServiceVersion", serviceVersionField));

        CtMethod addMethod = CtMethod.make("public int add(int a, int b) {int c = $1 + $2;\nSystem.out.println(\"c = \" + c);\nList/*<String>*/ list = new ArrayList/*<>*/();\nreturn c;\n}", ctClass);
        addMethod.insertBefore("System.out.println($1); System.out.println(\"start !!!\");");
        // 在原文件的指定行数添加代码
        //addMethod.insertAt(3, "System.out.println(\"haha\");");
        addMethod.insertAfter("System.out.println(\"end !!!\");");
        ctClass.addMethod(addMethod);

        // 添加构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), pool.get("long"), pool.get("java.lang.String")}, ctClass);
        // $0 代表 this 对象
        ctConstructor.setBody("{this.serviceVersion = $1; this.timeout = $2; this.name = $3;}");
        ctClass.addConstructor(ctConstructor);

        ctClass.writeFile(".");
    }
}
