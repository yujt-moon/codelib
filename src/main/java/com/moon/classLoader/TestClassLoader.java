package com.moon.classLoader;

import java.net.URL;

/**
 * 类加载器的层次结构
 * @author yujiangtao
 * @since 1.0
 */
public class TestClassLoader {
    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));

        ClassLoader loader = TestClassLoader.class.getClassLoader();
        while(loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
