package com.example.leetcode.solutions;

/**
 * @author renxinheng
 * @ClassName CountServersThatCommunicate
 * @createTime 2023/8/24
 * @desc 1267. 统计参与通信的服务器
 * 中等
 * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
 * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
 * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,0],[0,1]]
 * 输出：0
 * 解释：没有一台服务器能与其他服务器进行通信。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,0],[1,1]]
 * 输出：3
 * 解释：所有这些服务器都至少可以与一台别的服务器进行通信。
 * <p>
 * 示例 3：<image>/1267_untitled-diagram-1-3.jpg</image>
 * 输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * 输出：4
 * 解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 */
public class CountServersThatCommunicate {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        System.out.println(countServers(grid));
    }

    /**
     * 可以理解为从一个点出发  分别向四个方向行进,遇到一个点就重复
     * @param grid
     * @return
     */
    public static int countServers(int[][] grid) {

        return 0;
    }

    /**
     * 递归
     * @param grid
     * @param x
     * @param y
     * @param dir 方向: 0, 从左到右; 1,从上到下; 2,从右到左; 3,从下到上
     * @param count
     */
    private static void count(int[][] grid, int x, int y, int dir, int[] count){

    }
}
