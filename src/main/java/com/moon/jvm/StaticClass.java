package com.moon.jvm;

/**
 * @author yujiangtao
 * @date 2021/3/26 下午7:33
 */
public class StaticClass {

    public static String name = "hehe";


    static {
        name = "haha";
    }
}
