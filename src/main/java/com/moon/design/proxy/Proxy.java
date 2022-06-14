package com.moon.design.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by 12919 on 2018/1/7.
 */
public class Proxy {
    public static Object newProxyInstance(Class clazz, InvocationHandler h) throws Exception { //JDK6 Compiler API, cGLib, ASM
        String src =
                "package com.moon.design.proxy;\n" +
                "import java.lang.reflect.Method;" +
                "\n" +
                "public class $Proxy1 implements " + clazz.getName() + " {\n" +
                "    InvocationHandler h;\n" +
                "\n" +
                "    public $Proxy1(InvocationHandler h) {\n" +
                "        super();\n" +
                "        this.h = h;\n" +
                "    }\n" +
                "\n";

        Method[] methods = clazz.getMethods();
        for(Method m : methods) {
            src += "    @Override\n" +
                    "    public void " + m.getName() + "() {\n" +
                    "        try {\n" +
                    "           Method md = " + clazz.getName() + ".class.getMethod(\"" + m.getName() + "\");\n" +
                    "           h.invoke(this, md);\n" +
                    "        } catch(Exception e) {\n" +
                    "           e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n";

        }
        src += "}\n";


        String fileName = System.getProperty("user.dir") +
                "/src/main/java/com/moon/design/proxy/$Proxy1.java";
        FileWriter fw = new FileWriter(fileName);
        fw.write(src);
        fw.flush();
        fw.close();

        // compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileManager,
                null, null, null, units);
        t.call();
        fileManager.close();

        // load into memory and create an instance
        URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/src/main/java/")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.moon.design.proxy.$Proxy1");
        System.out.println(c);

        Constructor constructor = c.getConstructor(InvocationHandler.class);
        Object object = constructor.newInstance(h);

        return object;
    }
}
