package com.moon.concurrency.lock.mcs;

/**
 * @author yujiangtao
 * @date 2020/12/24 上午10:39
 */
public class QNode {
    boolean locked = false;

    QNode next = null;
}
