package com.example.leetcode.other.finger_guess;

/**
 * @author renxinheng
 * @ClassName FGUtils
 * @createTime 2023/10/20
 * @desc
 */
public class FGUtils {

    /**
     * 针对上次出牌,认为本次出同一张牌是小概率,然后针对剩下两张出牌
     * 取权重吧  1/3概率
     *
     * @param lastOpponent 对手上次出的牌
     * @return
     */
    public static Move getWinnerBaseOnLastChoose(Move lastOpponent) {
        if (lastOpponent == Move.SCISSORS) {
            return Move.PAPER;
        }
        if (lastOpponent == Move.ROCK) {
            return Move.SCISSORS;
        }
        if (lastOpponent == Move.PAPER) {
            return Move.ROCK;
        }
        return Move.values()[((int) System.currentTimeMillis() % 3)];
    }

    public static Move getWinnerBaseOnLastChoose1(Move lastOpponent) {
        //小概率反杀
        boolean littleProb = System.currentTimeMillis() % 4 == 0;
        if (lastOpponent == Move.SCISSORS) {
            return littleProb ? Move.ROCK : Move.PAPER;
        }
        if (lastOpponent == Move.ROCK) {
            return littleProb ? Move.PAPER : Move.SCISSORS;
        }
        if (lastOpponent == Move.PAPER) {
            return littleProb ? Move.SCISSORS : Move.ROCK;
        }
        return Move.values()[((int) System.currentTimeMillis() % 3)];
    }

    public static int fight(Move current, Move opponent){
        if (current == Move.NOOP|| opponent == Move.NOOP) return -1;
        if (current == opponent) return 0;
        if (current == Move.SCISSORS) return opponent == Move.PAPER? 1: -1;
        if (current == Move.ROCK) return opponent == Move.SCISSORS? 1: -1;
        if (current == Move.PAPER) return opponent == Move.ROCK? 1: -1;
        return -1;
    }

    //克制牌
    public static Move getRepressive(Move opponent) {
        if (opponent == Move.SCISSORS) return Move.ROCK;
        if (opponent == Move.ROCK) return Move.PAPER;
        return Move.SCISSORS;
    }
}
