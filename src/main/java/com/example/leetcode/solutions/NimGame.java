package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/9/18 10:18
 * @Description: 292. Nim 游戏
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：false
 * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 * 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：n = 2
 * 输出：true
 * <p>
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class NimGame {

    /**
     * 青玉案-元夕
     *          南宋-辛弃疾
     * 东风夜放花千树,更吹落,星如雨
     * 宝马雕车香满路
     * 凤箫声动,玉壶光转,一夜鱼龙舞
     * 蛾儿雪柳黄金缕,笑语盈盈暗香去
     * 众里寻他千百度,蓦然回首,那人却在,灯火阑珊处
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(canWinNim(5));
    }

    /**
     * 博弈论:不管是哪方,只要每次拿完后保证石头数是4的倍数,对手就赢不了,也就是说只要拿的时候不是4的整倍数,就能赢
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
