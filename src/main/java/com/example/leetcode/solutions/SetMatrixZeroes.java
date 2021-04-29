package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/4/6 14:51
 * @Description: 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 进阶：
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * <p>
 * 示例 1：
 * @image SetMatrixZeroes1.jpg
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * 示例 2：
 * @image SetMatrixZeroes2.jpg
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix = {{0, 1, 2, 1}, {3, 4, 5, 2}, {1, 3, 1, 0}};
        setZeroes(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 缓存置零过的行和列
     *
     * @param matrix
     */
    @Score(time = Score.S.SSS, memory = Score.S.C)
    public static void setZeroes(int[][] matrix) {
        boolean[] zeroLines = new boolean[matrix[0].length];//置零的行
        boolean[] zeroColumns = new boolean[matrix.length];//置零的列

        for (int col = 0; col < matrix.length; col++) {
            for (int lin = 0; lin < matrix[0].length; lin++) {
                if (matrix[col][lin] == 0) {
                    zeroColumns[col] = true;
                    zeroLines[lin] = true;
                }
            }
        }
        for (int col = 0; col < matrix.length; col++) {
            for (int lin = 0; lin < matrix[0].length; lin++) {
                if (zeroColumns[col] || zeroLines[lin])
                    matrix[col][lin] = 0;
            }
        }
    }
}
