package com.moon.jvm;

/**
 *
 * 暴力破解美团最新JVM面试题：无限执行（https://heapdump.cn/article/3478939）
 * @author yujiangtaoa
 * @date 2022/5/30 下午4:29
 */
public class TestRecursiveCall {
    public static void main(String[] args) {
        try {
            main(args);
        } catch (StackOverflowError error) {
            main(args);
        }
    }
}
