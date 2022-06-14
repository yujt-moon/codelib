package com.moon.concurrency;

/**
 * 查看该类的字节码
 *
 * @author yujiangtao
 * @date 2021/3/8 下午3:38
 */
public class SychronizedLock {

    private int count;

    /**
     * synchronized 修饰方法，不会体现在字节码中，在方法的修饰符中有 ACC_SYNCHRONIZED
     * @return
     */
    public synchronized int getCount() {
        return this.count;
    }

    /**
     * synchronized 修饰方法块，体现在字节码中是 monitorenter 和 monitorexit
     * @param count
     */
    public void setCount(int count) {
        synchronized (this) {
            this.count = count;
        }
    }

    /**
     * 通过字节码可以看到锁粗化的效果
     *
     * synchronized (this) {
     *     for (int i = 0; i < Integer.MAX_VALUE; i++) {
     *         System.out.println(i);
     *     }
     * }
     *
     */
    public void lockCoarsening() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            synchronized (this) {
                System.out.println(i);
            }
        }
    }

    /**
     * 锁消除
     *
     * 底层通过 StringBuilder 的 append() 方法实现
     * @return
     */
    public String concatStr() {
        String str1 = "Hello ";
        String str2 = "World, ";
        String str3 = "little boy!";

        return str1 + str2 + str3;
    }

    /**
     *
     * -XX:+DoEscapeAnalysis -XX:-EliminateLocks
     *
     * @return
     */
    public static String stringBuffer() {
        StringBuffer sb = new StringBuffer();
        sb.append("hello");
        sb.append(" yes");
        sb.append(" oh. haha");
        return sb.toString();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            stringBuffer();
        }
        long endTime = System.currentTimeMillis();
        // 55584 ms
        // 214582
        System.out.println("共耗时 " + (endTime - startTime) + " ms");
    }
}
