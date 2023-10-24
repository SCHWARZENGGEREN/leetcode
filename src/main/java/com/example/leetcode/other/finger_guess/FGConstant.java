package com.example.leetcode.other.finger_guess;

import java.util.Comparator;
import java.util.Random;

/**
 * @author renxinheng
 * @ClassName Constant
 * @createTime 2023/10/20
 * @desc
 */
public interface FGConstant {

    Random RANDOM = new Random();
    FGPlayer STRATEGY_RANDOM = (round, selfMoves, opponentMoves) -> Move.values()[RANDOM.nextInt(3)];
    Comparator<Move> MOVE_SORT = (o1, o2) -> {
        int i = o2.getNum() - o1.getNum();
        if (i == -2) return 1;
        if (i == 2) return -1;
        return i;
    };

}
