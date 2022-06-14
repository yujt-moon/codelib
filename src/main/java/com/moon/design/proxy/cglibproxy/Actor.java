package com.moon.design.proxy.cglibproxy;

/**
 * @author yujiangtao
 * @date 2020/8/18 下午5:20
 */
public class Actor {

    public final String getRealName() {
        return "big baby";
    }

    public String getWhat(String what) {
        return what;
    }
}
