package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2021/5/13 10:04
 * @Description: 1269. 停在原地的方案数
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * <p>
 * 示例 1：
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * <p>
 * 示例  2：
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * <p>
 * 示例 3：
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 * <p>
 * 提示：
 * <p>
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {

    public static void main(String[] args) {
        int steps = 3, arrLen = 2;
        System.out.println(numWays(steps, arrLen));
        System.out.println(numWays1(steps, arrLen));
    }

    /**
     * 使用动态数组dp[i][j] 记录i步后停留在j处的方案数
     * dp
     * @param steps
     * @param arrLen
     * @return
     */
    @Unsettled
    public static int numWays1(int steps, int arrLen) {
        //由于向右移动总要返回,那么最远能走到steps的一半 or arrLen
        int[][] dp = new int[steps + 1][Math.min(steps / 2, arrLen)];

        int bound = dp[0].length - 1;
        dp[0][0] = 1;
        for (int i = 0; i < bound; i++) {
            for (int j = 0; j < bound; j++) {

            }
        }
        return dp[steps - 1][0];
    }

    public static int numWays(int steps, int arrLen) {
        return sumNumWays(0, steps, arrLen, new HashMap<>());
    }

    /**
     * 按照排列组合,以routes[] 记录能到达的每个点的路线数
     * 那么,如果i处有x种选择,那么routes[i] =x*routes[i-1]
     *
     * @param pos
     * @param step
     * @param arrLen
     * @return
     */
    private static int sumNumWays1(int pos, int step, int arrLen) {
        if (step < pos || pos < 0 || pos >= arrLen) {
            return 0;//无法返回or超出边界
        } else if (step == pos) {
            return 1;//只能原路返回
        } else {
            step--;
            return sumNumWays1(pos, step, arrLen) +//原地不动
                    sumNumWays1(pos - 1, step, arrLen) + //向左
                    sumNumWays1(pos + 1, step, arrLen);//向右
        }
    }

    /**
     * 回溯 太慢
     *
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.OOM)
    private static int sumNumWays(int pos, int step, int arrLen, Map<Integer, Integer> stepsCache) {
        if (!stepsCache.containsKey(pos)) {
            if (step < pos || pos < 0 || pos >= arrLen) {
                stepsCache.put(pos, 0);//无法返回or超出边界
            } else if (step == pos) {
                stepsCache.put(pos, 1);//只能原路返回
            } else {
                step--;
                stepsCache.put(
                        pos,
                        sumNumWays(pos, step, arrLen, stepsCache) +//原地不动
                                sumNumWays(pos - 1, step, arrLen, stepsCache) + //向左
                                sumNumWays(pos + 1, step, arrLen, stepsCache)//向右
                );
            }
        }

        return stepsCache.get(pos);
    }
}
