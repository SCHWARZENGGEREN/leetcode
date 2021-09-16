package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/8/20 13:32
 * @Description: 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：[62_robot_maze.png]
 * <p>
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 * <p>
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths1(7, 3));
        System.out.println(uniquePaths1(3, 3));
        System.out.println(uniquePaths1(100, 100));
    }

    /**
     * 对于m和n 每次可以-1 or -0 ,有多少种组合
     * 回溯,超时
     *
     * @param m
     * @param n
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    /**
     * 使用状态转移方程
     *
     * @param m
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.B)
    public static int uniquePaths1(int m, int n) {
        if (m <= 1 || n <= 1) return 1;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    if (i > 0) dp[i][j] += dp[i - 1][j];
                    if (j > 0) dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
