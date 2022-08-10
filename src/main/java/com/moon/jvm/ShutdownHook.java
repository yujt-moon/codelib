package com.moon.jvm;

/**
 *
 * https://www.geeksforgeeks.org/jvm-shutdown-hook-java/
 * @author yujiangtaoa
 * @date 2022/7/26 下午8:40
 */
public class ShutdownHook {

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Shutdown Hook is running!");
            }
        }));
        System.out.println("Application Terminating ...");
        System.in.read();
    }
}
