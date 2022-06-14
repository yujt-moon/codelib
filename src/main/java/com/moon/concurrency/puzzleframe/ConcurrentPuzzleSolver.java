package com.moon.concurrency.puzzleframe;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

import static com.moon.concurrency.puzzleframe.SequentialPuzzleSolver.*;

/**
 * @author yujiangtao
 * @date 2019/3/17 9:14
 */
public class ConcurrentPuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;

    private final ExecutorService exec;

    private final ConcurrentMap<P, Boolean> seen;

    final ValueLatch<Node<P, M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec, ConcurrentMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.exec = exec;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            // 阻塞直到找到解答
            Node<P, M> solnNode = solution.getValue();
            return (solnNode == null) ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P, M> implements Runnable {

        public SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if(solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                return; // 已经找到解答或者已经遍历了这个位置
            }
            if(puzzle.isGoal(pos)) {
                solution.setValue(this);
            } else {
                for(M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m ,this));
                }
            }
        }
    }
}
