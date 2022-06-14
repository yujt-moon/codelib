package com.moon.concurrency;

/**
 * @author yujiangtaoa
 * @date 2022/6/10 下午5:38
 */
public class TestSequencePrintUsingCondition {

    private static Runnable getThreadA(final SequencePrintUsingCondition service) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10_000;i++) {
                    service.executeA();
                }
            }
        };
    }

    private static Runnable getThreadB(final SequencePrintUsingCondition service) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10_000;i++) {
                    service.executeB();
                }
            }
        };
    }

    private static Runnable getThreadC(final SequencePrintUsingCondition service) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10_000;i++) {
                    service.executeC();
                }
            }
        };
    }

    public static void main(String[] args) throws InterruptedException{
        SequencePrintUsingCondition service = new SequencePrintUsingCondition();
        Runnable A = getThreadA(service);
        Runnable B = getThreadB(service);
        Runnable C = getThreadC(service);

        new Thread(A, "A").start();
        new Thread(B, "B").start();
        new Thread(C, "C").start();
    }
}
