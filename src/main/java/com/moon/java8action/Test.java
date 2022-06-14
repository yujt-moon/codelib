package com.moon.java8action;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author yujiangtao
 * @date 2021/5/26 下午4:38
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        String collect = list.stream().collect(joining(","));
        System.out.println(collect);
    }
}
