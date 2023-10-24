package com.example.leetcode.other.finger_guess;

import java.util.List;

public interface FGPlayer {
    Move getMove(int round, List<Move> selfMoves, List<Move> opponentMoves);

    /**
     * 针对性策略,优先级 > 普通策略
     *
     * @param round
     * @param selfMoves
     * @param opponentMoves
     * @return
     */
    default Move getAimMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
        return null;
    }
}