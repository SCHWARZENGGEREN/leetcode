package com.example.leetcode.other.finger_guess;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * @author renxinheng
 * @ClassName FGRound
 * @createTime 2023/10/23
 * @desc 权重
 */
public class FGRound {

    private int SCISSORS_LIMIT = 30;
    private static List<Move> validMoves = Arrays.asList(Move.SCISSORS, Move.ROCK, Move.PAPER);

//    public FGRound(int SCISSORS_LIMIT, int ROCK_LIMIT, int PAPER_LIMIT, Move... exclusions) {
//        this.SCISSORS_LIMIT = SCISSORS_LIMIT;
//        this.ROCK_LIMIT = ROCK_LIMIT;
//        this.PAPER_LIMIT = PAPER_LIMIT;
//        if (!ArrayUtils.isEmpty(exclusions)) {
//            this.exclusions = Arrays.asList(exclusions);
//        }
//    }
//
//    public FGRound(Move... exclusions) {
//        this.SCISSORS_LIMIT = SCISSORS_LIMIT;
//        this.ROCK_LIMIT = ROCK_LIMIT;
//        this.PAPER_LIMIT = PAPER_LIMIT;
//        if (!ArrayUtils.isEmpty(exclusions)) {
//            this.exclusions = Arrays.asList(exclusions);
//        }
//    }

    private int ROCK_LIMIT = 60;
    private int PAPER_LIMIT = 90;
    private List<Move> exclusions;


    /**
     * 按照权重随机
     *
     * @return
     */
    public static Move getRoundMove(Map<Move, Integer> weightMap, Random random) {
        Integer round = 0;
        List<Move> movesList = new ArrayList<>(weightMap.keySet());
        List<Integer> weightList = new ArrayList<>();
        for (Move move : movesList) {
            weightList.add(weightMap.get(move));
        }
        movesList.sort(Comparator.comparing(weightMap::get));
        weightList.sort(Integer::compareTo);
        round = weightList.get(weightList.size() - 1);
        int randomI = random.nextInt(round);
        for (int i = 0; i < weightList.size(); i++) {
            if (randomI < weightList.get(i)) {
                return movesList.get(i);
            }
        }
        return movesList.get(0);
    }
}
