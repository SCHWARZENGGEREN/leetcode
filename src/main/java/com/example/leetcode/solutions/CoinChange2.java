package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/6/10 15:08
 * @Description: 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 */
public class CoinChange2 {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println(change(amount, coins));
    }

    /**
     * 和爬楼梯类似,只不过将固定的一阶或二阶改成不固定的硬币面额
     *
     * @param amount
     * @param coins
     * @return
     * @see ClimbingStairs
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];//每个金额对应的硬币挑选种数
        dp[0] = 1;
        //外层遍历coin,每个组合coin从小到大排列
        for (int coin : coins) {
            //为避免排列重复,不使用之前的coin
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];//以当前硬币为最大硬币面额的组合
            }
        }

//        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}
