package com.moon.util;

import java.util.concurrent.TimeUnit;

/**
 * @author yujiangtaoa
 * @date 2022/7/21 下午2:25
 */
public class SleepHelper {

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep((long) seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
