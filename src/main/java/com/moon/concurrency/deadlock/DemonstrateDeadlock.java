package com.moon.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yujiangtao
 * @date 2019/3/17 11:29
 */
public class DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;

    private static final int NUM_ACCOUNT = 5;

    public static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNT];

        for(int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for(int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNT);
                    int toAcct = rnd.nextInt(NUM_ACCOUNT);
                    DollarAmount amount =
                            new DollarAmount(rnd.nextInt(1000));
                    try {
                        transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (InsufficientFundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }

    // Warning: deadlock-prone!
    public static void transferMoney(Account fromAccount, Account toAccount,
                                     DollarAmount amount)
            throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if(fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    static class DollarAmount implements Comparable<DollarAmount> {
        private int amount;

        @Override
        public int compareTo(DollarAmount o) {
            return amount - o.amount;
        }

        public DollarAmount(int amount) {
            this.amount = amount;
        }

        public DollarAmount add(DollarAmount d) {
            return new DollarAmount(d.amount + amount);
        }

        public DollarAmount sustract(DollarAmount d) {
            return new DollarAmount(d.amount - amount);
        }
    }

    static class Account {
        private DollarAmount balance;

        private final int accNo;

        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            accNo = sequence.incrementAndGet();
            balance = new DollarAmount(1000000);
        }

        void debit(DollarAmount d) {
            balance = balance.sustract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAccNo() {
            return accNo;
        }
    }
}
