package com.example.leetcode.solutions;

/**
 * @author renxinheng
 * @ClassName Largest1BorderedSquare
 * @createTime 2023/10/12
 * @desc 1139. 最大的以 1 为边界的正方形
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，
 * 并返回该子网格中的元素数量。如果不存在，则返回 0。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * <p>
 * 示例 2：
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 * @{@link https://leetcode.cn/problems/largest-1-bordered-square/description/}
 */
public class Largest1BorderedSquare {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(largest1BorderedSquare(grid));
    }

    /**
     * 找出最大正方形的边长
     *
     * @param grid
     * @return
     */
    public static int largest1BorderedSquare(int[][] grid) {
        int maxBoard = 0;
        //分别统计角标向右最长的连续1 和向下最长的连续1
        int[][] RIGHT = new int[grid.length][grid[0].length];
        int[][] DOWN = new int[grid.length][grid[0].length];
        //todo RIGHT & DOWN


        return maxBoard * maxBoard;
    }

    public static final int DIR_RIGHT = 1;
    public static final int DIR_LEFT = -1;
    public static final int DIR_UP = 2;
    public static final int DIR_DOWN = -2;
    /**
     * 从当前位置出发,经过destLen个位置全部是1?
     * @param idx 横轴角标
     * @param idy 纵轴角标
     * @param grid 矩阵
     * @param dir 行进方向: 1-> 右; -1, 左; 2-> 上; -2 -> 下
     * @return 是否满足条件
     */
    private static int getMaxLen(int idx, int idy, int[][] grid, int dir) {
        int maxLen = 0;
        int yLen = grid.length;
        int xLen = grid[0].length;
        if (dir == DIR_RIGHT){
            for (int i = idx; i < xLen; i ++){
                if (grid[idy][i] == 0) break;
                maxLen ++;
            }
        }else if (dir == DIR_LEFT) {
            for (int i = idx; i > 0; i --){
                if (grid[idy][i] == 0) break;
                maxLen ++;
            }
        }else if (dir == DIR_UP) {
            for (int i = idy; i < yLen; i ++){
                if (grid[i][idx] == 0) break;
                maxLen ++;
            }
        }else if (dir == DIR_DOWN) {
            for (int i = idy; i > 0; i --){
                if (grid[i][idx] == 0) break;
                maxLen ++;
            }
        }
        return maxLen;
    }
}
