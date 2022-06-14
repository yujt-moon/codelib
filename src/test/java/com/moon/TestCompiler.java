package com.moon;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

/**
 *
 * @author yujiangtao
 * @since 1.0
 */
public class TestCompiler {
    static String str = "package test;\n" +
            "\n" +
            "import com.moon.util.URLUtils;\n" +
            "\n" +
            "import java.util.HashMap;\n" +
            "import java.util.Map;\n" +
            "\n" +
            "/**\n" +
            " * @author yujiangtao\n" +
            " * @since 1.0\n" +
            " */\n" +
            "public class Test {\n" +
            "\n" +
            "    public static void main(String[] args) {\n" +
            "        String urlStr = \"http://bjuc.digi-zones.com/wt/webpage/common/weixin/controlOpenId.jsp\";\n" +
            "        System.out.println(URLUtils.encodeUrl(urlStr));\n" +
            "        System.out.println(URLUtils.decodeUrl(URLUtils.encodeUrl(urlStr)));\n" +
            "\n" +
            "        System.out.println(URLUtils.decodeUrl(\"http%3a%2f%2fdqwechat.bbn.com.cn%2fwangting%2fwebpage%2fcommon%2fweixin%2fcontrolOpenId.jsp\"));\n" +
            "\n" +
            "        byte[] arr = new byte[10];\n" +
            "        arr[0] = 0;\n" +
            "        arr[1] = 2;\n" +
            "        arr[2] = -127;\n" +
            "        // ?This is why\n" +
            "        System.out.println(arr[2]&0xff);\n" +
            "        System.out.println(0xff);\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 获取名字\n" +
            "     * @param name\n" +
            "     * @return\n" +
            "     */\n" +
            "    public static String getDesc(String name) {\n" +
            "        System.out.println(name);\n" +
            "        return name;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 获取map\n" +
            "     * @param name 用户名\n" +
            "     * @param age 用户的年龄\n" +
            "     * @return\n" +
            "     */\n" +
            "    private Map<String, String> getMap(String name, Integer age) {\n" +
            "        Map<String, String> map = new HashMap<String, String>();\n" +
            "        map.put(\"name\", name);\n" +
            "        map.put(\"age\", age.toString());\n" +
            "        return map;\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) throws Exception {
        // 将生成的Java文件写到C盘test文件夹下
        File file = new File("C:/test/Test.java");
        FileWriter fw = new FileWriter(file);
        fw.write(str);
        fw.flush();
        fw.close();

        // compiler the java file into class file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.
                getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(new File("C:/test/Test.java"));
        JavaCompiler.CompilationTask task = compiler.
                getTask(null, fileManager, null, null, null, units);
        task.call();
        fileManager.close();
        URL[] urls = new URL[]{new URL("file://C:/test/Test.java")};
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> c = loader.loadClass("test.Test");
        System.out.println("类加载器加载的类名：" + c.getName());

        // 获取构造函数
        Constructor<?> constructor = c.getConstructor();
        // 构造对象
        Object o = constructor.newInstance();
        // 获取main方法
        Method methodMain = c.getDeclaredMethod("main", String[].class);
        // 调用main方法
        methodMain.invoke(c, new Object[]{new String[]{}});

        // 获取getName方法
        Method getNameMethod = c.getMethod("getDesc", String.class);
        // 调用getName方法
        Object returnName = getNameMethod.invoke(o, "hehe");
        System.out.println("获取返回的Name：" + returnName);

        // 获取私有的getMap方法
        Method getMapMethod = c.getDeclaredMethod("getMap", String.class, Integer.class);
        getMapMethod.setAccessible(true);
        // 调用getMap方法
        Map<String, String> result = (Map<String, String>) getMapMethod.invoke(o, "haha", new Integer(12));
        System.out.println("getMap返回值：" + result);
    }
}
