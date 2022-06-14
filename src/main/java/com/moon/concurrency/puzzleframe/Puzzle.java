package com.moon.concurrency.puzzleframe;

import java.util.Set;

/**
 * @author yujiangtao
 * @date 2019/3/16 14:33
 */
public interface Puzzle<P, M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}
