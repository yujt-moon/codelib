package com.moon.java8action.chapter12;

import java.time.LocalTime;

/**
 * @author yujiangtao
 * @date 2020/7/13 下午5:35
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
    }
}
