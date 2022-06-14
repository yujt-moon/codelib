package com.moon.jvm;

/**
 *
 * vmoption: -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 * @author yujiangtao
 * @date 2021/4/29 下午5:40
 */
public class HeapAlloc {
    public static void main(String[] args) {
        System.out.printf("maxMemory = %d bytes\n", Runtime.getRuntime().maxMemory());
        System.out.printf("free mem = %d bytes\n", Runtime.getRuntime().freeMemory());
        System.out.printf("total mem = %d bytes\n", Runtime.getRuntime().totalMemory());

        byte[] b = new byte[1*1024*1024];
        System.out.println("分配了1M 空间给数组");

        System.out.printf("maxMemory = %d bytes\n", Runtime.getRuntime().maxMemory());
        System.out.printf("free mem = %d bytes\n", Runtime.getRuntime().freeMemory());
        System.out.printf("total mem = %d bytes\n", Runtime.getRuntime().totalMemory());

        b = new byte[4*1024*1024];
        System.out.println("分配了4M 空间给数组");

        System.out.printf("maxMemory = %d bytes\n", Runtime.getRuntime().maxMemory());
        System.out.printf("free mem = %d bytes\n", Runtime.getRuntime().freeMemory());
        System.out.printf("total mem = %d bytes\n", Runtime.getRuntime().totalMemory());
    }
}
