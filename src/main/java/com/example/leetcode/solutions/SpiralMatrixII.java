package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.utils.ArrayUtil;

/**
 * @author renxinheng
 * @ClassName SpiralMatrixII
 * @createTime 2023/8/23
 * @desc 59. 螺旋矩阵 II
 * 中等
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
 * 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * <image>/59_spiraln.jpg</image> /r/n
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * 1  2  3  4  5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * <p>
 * 1  2  3  4  5  6  7  8
 * 28 29 30 31 32 33 34 9
 * 27 48 49 50 51 52 35 10
 * 26 47 60 61 62 53 36 11
 * 25 46 59 64 63 54 37 12
 * 24 45 58 57 56 55 38 13
 * 23 44 43 42 41 40 39 14
 * 22 21 20 19 18 17 16 15
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 */
public class SpiralMatrixII {

    public static void main(String[] args) {
        ArrayUtil.printMultiArray(generateMatrix(30));
    }

    /**
     * 旋转矩阵
     * 简单粗暴,顺着方向走就完了
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SSS)
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int x = 0, y = 0, i = 1, ns = n * n;
        while (true) {
            // >
            while (y < n && matrix[x][y] == 0) {
                matrix[x][y] = i++;
                y++;
            }
            y--;//战术后撤步
            x++;//战术转向
            // V
            while (x < n && matrix[x][y] == 0) {
                matrix[x][y] = i++;
                x++;
            }
            x--;
            y--;
            // <
            while (y >= 0 && matrix[x][y] == 0) {
                matrix[x][y] = i++;
                y--;
            }
            y++;
            x--;
            //^
            while (x >= 0 && matrix[x][y] == 0) {
                matrix[x][y] = i++;
                x--;
            }
            x++;
            y++;
            //防止死循环
            if (i > ns) return matrix;
        }
    }
}
