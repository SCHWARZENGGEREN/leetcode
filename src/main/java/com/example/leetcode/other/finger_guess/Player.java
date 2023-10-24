package com.example.leetcode.other.finger_guess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author renxinheng
 * @ClassName Player
 * @createTime 2023/10/20
 * @desc
 */
public abstract class Player implements FGPlayer {

    private String playerNum;//玩家编号
    private String slogan;//口号!
    private List<Integer> result;//每局结果

    public Player(String playerNum, String slogan) {
        this.playerNum = playerNum;
        this.slogan = slogan;
        this.result = new ArrayList<>();
    }
    public Player() {
        this.playerNum = playerNum;
    }

    public String getPlayerNum() {
        return playerNum;
    }

    public String getSlogan() {
        return slogan;
    }

    protected void setPlayerNum(String playerNum) {
        this.playerNum = playerNum;
    }

    protected void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Move getFinalMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
        Move baseMove = null;
        Move aimMove = null;
        return Objects.nonNull(aimMove = getAimMove(round, selfMoves, opponentMoves)) ? aimMove :
                Objects.nonNull(baseMove = getMove(round, selfMoves, opponentMoves)) ?
                        baseMove : Move.PAPER;
    }
}
