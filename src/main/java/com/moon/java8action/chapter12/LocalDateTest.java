package com.moon.java8action.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author yujiangtao
 * @date 2020/7/13 下午5:28
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
    }
}
