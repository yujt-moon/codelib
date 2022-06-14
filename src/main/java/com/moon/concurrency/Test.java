package com.moon.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujiangtao
 * @date 2021/2/10 下午9:42
 */
public class Test {
    public static void main(String[] args) {
        Map<Integer, Object> map = new ConcurrentHashMap<>();
        map.put(0, new Object());
        System.out.println(map);
    }
}
