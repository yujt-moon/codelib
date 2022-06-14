package com.moon.concurrency.lock.clh;

import com.moon.concurrency.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * CLH lock is Craig, Landin, and Hagersten (CLH) locks, CLH lock is a spin lock, can ensure no hunger,
 * provide fairness first come first service.
 * The CLH lock is a scalable, high performance, fairness and spin lock based on the list,
 * the application thread spin only on a local variable, it constantly polling the precursor state,
 * if it is found that the pre release lock end spin.
 *
 * @author yujiangtao
 * @date 2020/12/22 下午2:37
 */
public class CLHLock implements Lock {

    // 尾巴，是所有线程共有的一个。所有线程进来后，把自己设置为 tail
    private final AtomicReference<QNode> tail;

    // 前驱节点，每个线程独有一个
    private final ThreadLocal<QNode> myPred;

    // 当前节点，表示自己，每个线程独有一个
    private final ThreadLocal<QNode> myNode;

    public CLHLock() {
        this.tail = new AtomicReference<QNode>(new QNode());
        this.myNode = new ThreadLocal<QNode>() {
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };
        this.myPred = new ThreadLocal<QNode>();
    }

    @Override
    public void lock() {
        // 获取当前线程的代表节点
        QNode node = myNode.get();
        // 将自己的状态设置为 true 表示获取锁
        node.locked = true;
        // 将自己放在队列尾巴，并且返回以前的值。第一次进将获取构造函数中的那个 new QNode()
        QNode pred = tail.getAndSet(node);
        // 把旧的节点放入前驱节点
        myPred.set(pred);
        // 判断前驱节点的状态，然后走掉
        while(pred.locked) {}
    }

    @Override
    public void unlock() {
        // unlock 获取自己的 node，把自己的 locked 设置为 false
        QNode node = myNode.get();
        node.locked = false;
        //
        myNode.set(myPred.get());
    }
}
