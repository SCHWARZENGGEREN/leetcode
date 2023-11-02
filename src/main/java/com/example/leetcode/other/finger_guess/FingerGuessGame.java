package com.example.leetcode.other.finger_guess;

import java.util.*;

/**
 * @author renxinheng
 * @ClassName FingerGuessGame
 * @createTime 2023/10/20
 * @desc
 */
public class FingerGuessGame {

    public static void main(String[] args) {
        Player playerA = PlayerEnum.BlackTiger.callYourHero();//主场
//        playerA = new CrazyDave();
//        playerA.setPlayerNum("疯狂戴夫");
//        playerA.setSlogan("歪比歪比 歪比巴卜");
        Player playerB = PlayerEnum.AI.callYourHero();//客场

        new PlayerContext(playerA, playerB).start(100);

//        System.out.println(UUID.randomUUID().toString());
    }

    public static class PlayerContext {
        private Player playerA;
        private Player playerB;

        private Map<String, List<Integer>> scoreMap;//得分
        private Map<String, List<Move>> movesMap;//出牌记录
        private Map<Integer, Integer> scoreCalcMap;//评分规则

        public PlayerContext(Player playerA, Player playerB) {
            this.playerA = playerA;
            this.playerB = playerB;
            scoreMap = new HashMap<>();
            scoreMap.put(playerA.getPlayerNum(), new ArrayList<>());
            scoreMap.put(playerB.getPlayerNum(), new ArrayList<>());

            movesMap = new HashMap<>();
            movesMap.put(playerA.getPlayerNum(), new ArrayList<>());
            movesMap.put(playerB.getPlayerNum(), new ArrayList<>());

            scoreCalcMap = new HashMap<>();
            scoreCalcMap.put(-1, 0);
            scoreCalcMap.put(0, 0);
            scoreCalcMap.put(1, 10);
        }

        //回合战
        public void battle(int round) {
            Move playerAMove = playerA.getFinalMove(round, movesMap.get(playerA.getPlayerNum()), movesMap.get(playerB.getPlayerNum()));
            Move playerBMove = playerB.getFinalMove(round, movesMap.get(playerB.getPlayerNum()), movesMap.get(playerA.getPlayerNum()));
            movesMap.get(playerA.getPlayerNum()).add(playerAMove);
            movesMap.get(playerB.getPlayerNum()).add(playerBMove);

            int battle = playerAMove.battle(playerBMove);
            int aScore = scoreCalcMap.get(battle);
            int bScore = scoreCalcMap.get(battle * -1);
            scoreMap.get(playerA.getPlayerNum()).add(aScore);
            scoreMap.get(playerB.getPlayerNum()).add(bScore);

            System.out.printf(
                    "第[%d]局: 玩家[%s] 出: %s; 玩家[%s] 出: %s; ",
                    round, playerA.getPlayerNum(), playerAMove.getDesc(), playerB.getPlayerNum(), playerBMove.getDesc()
            );
            System.out.println(battle == 0 ? "平局!" : String.format(
                    "玩家[%s] 胜出!", battle > 0 ? playerA.getPlayerNum() : playerB.getPlayerNum()
            ));
        }

        //评定胜负
        public void scoreAssessment() {
            int totalScoreA = scoreMap.get(playerA.getPlayerNum()).stream().mapToInt(Integer::valueOf).sum();
            int totalScoreB = scoreMap.get(playerB.getPlayerNum()).stream().mapToInt(Integer::valueOf).sum();
            int battleRes = totalScoreA - totalScoreB;
            System.out.printf("比赛结束, 选手[%s] 总分为: %d; 选手[%s] 总分为: %d; ", playerA.getPlayerNum(), totalScoreA, playerB.getPlayerNum(), totalScoreB);
            System.out.println("比赛结果: " + (
                    battleRes == 0 ? "平局" :
                            String.format("选手[%s]胜出!", battleRes > 0 ? playerA.getPlayerNum() : playerB.getPlayerNum())
            ));
        }

        public void start(int totalRound) {
            //玩家互报名号环节
            System.out.println("============玩家自我介绍环节=============");
            System.out.printf("[%s]: %s %n", playerA.getPlayerNum(), playerA.getSlogan());
            System.out.printf("[%s]: %s %n", playerB.getPlayerNum(), playerB.getSlogan());
            System.out.println("==============比赛正式开始===============");
            int i = 1;
            while (i <= totalRound) {
                battle(i++);
            }
            System.out.println("============比赛结束 开始评分=============");
            scoreAssessment();
        }
    }
}
