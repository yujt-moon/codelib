package com.moon.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yujiangtao
 * @date 2019/2/17 14:09
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            public void run() {
                mainBoard.commitNewValues();
            }
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    public int computeValue(int x, int y) {
        return x + y;
    }

    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }

    private class Worker implements Runnable {
        private final Board board;
        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while(!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }
    }
}

class Board {

    public void commitNewValues() {

    }

    public Board getSubBoard(int count, int i) {
        return null;
    }

    public boolean hasConverged() {
        return false;
    }

    public int getMaxX() {
        return 0;
    }

    public int getMaxY() {
        return 0;
    }

    public void setNewValue(int x, int y, int i) {

    }

    public void waitForConvergence() {

    }
}
