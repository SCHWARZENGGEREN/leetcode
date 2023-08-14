package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName MatrixDiagonalSum
 * @createTime 2023/8/11
 * @desc 1572. 矩阵对角线元素的和
 * 提示
 * 简单
 * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
 * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
 * <p>
 * 示例  1：
 * 输入：mat = [[1,2,3],
 * [4,5,6],
 * [7,8,9]]
 * 输出：25
 * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
 * 请注意，元素 mat[1][1] = 5 只会被计算一次。
 * <p>
 * 示例  2：
 * 输入：mat = [[1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1]]
 * 输出：8
 * <p>
 * 示例 3：
 * 输入：mat = [[5]]
 * 输出：5
 * <p>
 * 提示：
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixDiagonalSum {

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(diagonalSum(mat));
        System.out.println(diagonalSum1(mat));
    }

    @Score(time = Score.S.SSS,memory = Score.S.C)
    public static int diagonalSum(int[][] mat) {
        int sum = 0;
        int i = 0;
        int length = mat.length;
        while (i < length) {
            sum += mat[i][i];//左上->右下方向
            if (i << 1 != length - 1) {
                //防止重复
                sum += mat[i][length - 1 - i];//右上->左下
            }
            i++;
        }
        return sum;
    }

    /**
    优化,可以认为是从正方形中间往四个角发散对角线,每次计算四个角对的值
     */
    @Score(time = Score.S.SSS,memory = Score.S.B)
    public static int diagonalSum1(int[][] mat) {
        int sum = 0;
        int length = mat.length;
        int i = length / 2;
        if (i << 1 != length) {
            //奇数
            sum += mat[i][i++];//中心点/角标偏移
        }
        //开始计算
        for (; i < length; i++) {
            sum += mat[i][i]; //右下点
            sum += mat[length - 1 - i][length - 1 - i]; //左上点
            sum += mat[length - 1 - i][i]; //右上点
            sum += mat[i][length - 1 - i]; //左下点
        }
        return sum;
    }

}
