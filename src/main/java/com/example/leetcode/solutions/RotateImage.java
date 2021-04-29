package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2020/1/10 15:14
 * @Description: 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-image
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.deepToString(arr));
        rotate1(arr);
        System.out.println(Arrays.deepToString(arr));
    }

    /**
     * 将二维数组旋转
     * 分别自内向外一层一层处理,先把x+轴的元素移到temp,然后将y-的移到x+;然后将x-移到y--;然后将y+移到x-;最后将temp中的x+移动到y+
     *  TODO 时间开销非常少,但内存消耗有点多(WHY???)
     *
     * @param matrix
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        if (n > 1) {
            int m = n / 2, i, tmp;
            while (m > 0) {
                i = m - 1;
                while (i < n - m) {
                    //将x+放入temp
                    tmp = matrix[m - 1][i];
                    //将y-放到x+
                    matrix[m - 1][i] = matrix[n - i - 1][m - 1];
                    //将x-放到y-
                    matrix[n - i - 1][m - 1] = matrix[n - m][n - i - 1];
                    //将y+放到x-
                    matrix[n - m][n - i - 1] = matrix[i][n - m];
                    //将tmp放到y+
                    matrix[i][n - m] = tmp;
                    i++;
                }
                m--;
            }
        }
    }
}
