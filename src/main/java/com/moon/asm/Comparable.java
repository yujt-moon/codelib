package com.moon.asm;

/**
 * @author yujiangtao
 * @date 2018/7/23 16:31
 */
public interface Comparable extends Mesurable {
    int LESS = -1;
    int EQUAL = 0;
    int GREATER = 1;
    int compareTo(Object o);
}
