package com.example.leetcode.other.finger_guess;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renxinheng
 * @ClassName CrazyDave
 * @createTime 2023/10/23
 * @desc Wabby Wabby Wabbybobo
 */
public class CrazyDave extends Player {

    private Random random = new Random(System.currentTimeMillis() + 10086);

    /**
     * 出牌策略:
     *  1,针对重复出牌情况,如果对手连续出一张牌,那么依据其重复出牌次数,直接出克制牌.
     *      出牌次数越多,直接出克制牌概率越高,工时:prob =
     * @param round
     * @param selfMoves
     * @param opponentMoves
     * @return
     */
    @Override
    public Move getMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
        if (CollectionUtils.isEmpty(opponentMoves)) return getRandomMove();//第一局 直接随机
        //观察对手出牌
        //没有重复出牌or重复比较少的情况 如果上次出石头 那么这次依然出石头就是低概率,就是说大概率出剪刀or布 那么我出剪刀
        int size = opponentMoves.size();
        Move lastOpponentMove = opponentMoves.get(size - 1);
        if (size <= 1) return getWinnerBaseOnLastChoose(lastOpponentMove);
        //先看对手有无重复出牌 如果有 根据重复次数判断要不要直接出重复牌的克制牌
        //次数越多 随机权重越高 超过5次直接不随机了
        int j = 0;
        for (int i = size - 2; i >= 0; i--, j++) {
            if (j >= 5) return getWinner(lastOpponentMove);
            if (opponentMoves.get(i) != lastOpponentMove) break;
        }
        if (j > 0) {
            //公式:(1+j*0.2)*random > 1 陡函数
            if ((1 + j * (j + 0.1d)) * random.nextDouble() >= 1) {
                return getWinner(lastOpponentMove);
            }
        }
        //针对上次
        return getWinnerBaseOnLastChoose(lastOpponentMove);
    }

    //依据连续出同一张牌是小概率时间,认为本局出牌大概率跟上一局不同,比如对手上一局出石头 那么这一句大概率剪刀/布 那我出剪刀的话 赢面就比较高
    //进一步优化 针对对手上一局出牌,如果是石头,我这局50%出剪刀(克制or平局剩下俩),30%出石头(克制另一个,但一半概率被克制),20%出布,出牌小概率跟上一局一样
    private Move getWinnerBaseOnLastChoose(Move lastOpponent) {
        //小概率反杀
        boolean littleProb = random.nextDouble() * 4 <= 1;
        if (lastOpponent == Move.SCISSORS) {
            return littleProb ? Move.ROCK : Move.PAPER;
        }
        if (lastOpponent == Move.ROCK) {
            return littleProb ? Move.PAPER : Move.SCISSORS;
        }
        if (lastOpponent == Move.PAPER) {
            return littleProb ? Move.SCISSORS : Move.ROCK;
        }
        return getRandomMove();
    }

    private Move getRandomMove() {
        return Move.values()[random.nextInt(3)];
    }

    private static Move getWinner(Move opponent) {
        if (opponent == Move.SCISSORS) return Move.ROCK;
        if (opponent == Move.ROCK) return Move.PAPER;
        return Move.SCISSORS;
    }

    public static final Pattern WHITE_LIST = Pattern.compile("^/([a-z0-9]+?)/(home|index|help|login|logout|logging|404|error|account/reset-self-password|auth-code|no-token/\\S+|cmp_sp/\\S+)?$");
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("/booking/no-token/login/", "/schedulerplus/cmp_sp/dispatch", "/booking/no-token/login/", "/booking/no-token/login/", "/booking/city/list/");
        for (String string : strings) {
            Matcher matcher = WHITE_LIST.matcher(string);
            System.out.println(string + "--" + matcher.matches());
        }
    }
}
