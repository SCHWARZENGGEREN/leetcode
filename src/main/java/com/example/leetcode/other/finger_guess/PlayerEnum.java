package com.example.leetcode.other.finger_guess;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 强者如林啊
 */
public enum PlayerEnum {
    AI("AI(平平无奇)", "简单粗暴,不服来干", FGConstant.STRATEGY_RANDOM),
    DAVE("隔壁老戴", "歪比歪比, 歪比巴卜", FGConstant.STRATEGY_RANDOM),
    TL("时间城主", "掌无限于掌心,驻永恒于片刻", (round, selfMoves, opponentMoves) -> {
        //时间算法
        try {
            Thread.sleep(31);
        }catch (Exception e){}
        return Move.values()[(int) System.currentTimeMillis() % 3];
    }),
    DemonWarrior("无敌最魔人", "无敌是多么寂寞", (round, selfMoves, opponentMoves) -> {
        //拳头比较男人 所以我只出拳头
        return Move.ROCK;
    }),
    SuperWarrior("无敌最魔人", "无敌是多么寂寞", (round, selfMoves, opponentMoves) -> {
        //AAAAABBBBBCCCCC
        return Move.values()[(round / 5) % 3];
    }),
    BlackTiger("黑虎阿福", "乌鸦坐飞机!", (round, selfMoves, opponentMoves) -> {
        //我的招式,多到你难以想象
        //随机,且保障每次必跟上次不重复不重复
        if (CollectionUtils.isEmpty(selfMoves)) {
            return FGConstant.STRATEGY_RANDOM.getMove(round, selfMoves, opponentMoves);
        }
        Move lastMove = selfMoves.get(selfMoves.size() - 1);
        return Arrays.stream(Move.values()).filter(m -> m != lastMove && m != Move.NOOP)
                .collect(Collectors.toList()).get(FGConstant.RANDOM.nextInt(2));
    }),
    LOONG("山龙隐秀", "紫薇无姓,红尘留行,扁舟越沧溟", new FGPlayer() {
        @Override
        public Move getMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
            //[一字诀]
            //观察对手出牌 如果上次出石头 那么这次依然出石头就是低概率,就是说大概率出剪刀or布 那么我出剪刀的话
            if (CollectionUtils.isNotEmpty(opponentMoves)) {
                Move lastOpponentMove = opponentMoves.get(opponentMoves.size() - 1);
                //如果上次他出石头 那么这次我就出剪刀
                return FGUtils.getWinnerBaseOnLastChoose1(lastOpponentMove);
            }
            return FGConstant.STRATEGY_RANDOM.getMove(round, selfMoves, opponentMoves);
        }

        @Override
        public Move getAimMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
//            if (CollectionUtils.isNotEmpty(opponentMoves)){
//                //如果对方连续出一张牌 直接伪随机吧 1/3概率继续出同一张牌
//                if (opponentMoves.size() > 1 && opponentMoves.get(opponentMoves.size() - 1) ==
//                        opponentMoves.get(opponentMoves.size() - 2)
//                        && System.currentTimeMillis()%3==0) {
//                    return FGUtils.getWinner(opponentMoves.get(opponentMoves.size() - 1));
//                }
//            }
//            return FingerGuess.super.getAimMove(round, selfMoves, opponentMoves);
            return null;
        }
    }),
    ;
    private String playerName;
    private String slogan;
    private FGPlayer playerStrategy;

    PlayerEnum(String playerName, String slogan, FGPlayer playerStrategy) {
        this.playerName = playerName;
        this.slogan = slogan;
        this.playerStrategy = playerStrategy;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getSlogan() {
        return slogan;
    }

    public FGPlayer getPlayerStrategy() {
        return playerStrategy;
    }

    public Player callYourHero() {
        return new Player(playerName, slogan) {
            @Override
            public Move getMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
                return playerStrategy.getMove(round, selfMoves, opponentMoves);
            }

            @Override
            public Move getAimMove(int round, List<Move> selfMoves, List<Move> opponentMoves) {
                return playerStrategy.getAimMove(round, selfMoves, opponentMoves);
            }
        };
    }
}