package com.example.leetcode.solutions;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/10/29 13:53
 * @Description: 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 * <p>
 * <p>
 * 示例：
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *  
 * <p>
 * 提示：
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game
 */
public class StoneGame {

    public static void main(String[] args) {
        int[] piles = {5, 100, 101, 5};
        System.out.println("stones: " + Arrays.toString(piles));
        System.out.println("game: " + stoneGame1(piles));
    }

    /**
     * 题目可以描述为 :
     * 这一堆石子队列是否满足:两人每次都拿头尾中最多的那堆,第一个人到最后恰好拿的最多
     * 或者,一个人第一次拿头尾多的那堆,然后丢掉头尾多的那堆,然后他在拿,如此循环,到最后他拿的石子数比丢掉的多
     *
     * 博弈论
     *
     * @param piles
     * @return
     */
    public static boolean stoneGame1(int[] piles) {
        int len = piles.length, head = 0, tail = len - 1, tokeCount = 0, thrownCount = 0, add = 0;
        for (int i = 0; i < len; i++) {
            if (piles[head] >= piles[tail]) {
                //如果前面的多就直接拿前面的
                add = piles[head];
                head++;
            } else if (piles[head] < piles[tail]) {
                //直接拿多的那一堆
                add = piles[tail];
                tail--;
            }
            if (i % 2 == 0) {
                tokeCount += add;
            } else {
                thrownCount += add;
            }

        }
        System.out.println("game end , tokeCount: " + tokeCount + " ; thrownCount: " + thrownCount);
        return tokeCount > thrownCount;
    }
}
