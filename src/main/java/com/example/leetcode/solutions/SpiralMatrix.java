package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2020/8/26 15:33
 * @Description: 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        int[][] matrix1 =
                {
                        {1, 2, 3},
                        {1, 2, 3},
                        {1, 2, 3},
                        {1, 2, 3}
                };
        int[][] matrix2 =
                {
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6}
                };
        System.out.println(spiralOrder(matrix1));
    }

    /**
     * 找出每个方向的x,y坐标的函数
     *
     * @param matrix
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.B)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length <= 0)
            return result;
        int x = 0, y = 0, direct = 1,
                xLen = matrix.length, yLen = matrix[0].length,
                total = xLen * yLen,
                step = 0;

        while (total > 0) {
            switch (direct) {
                case 1://y++
                    step = yLen - y * 2;
                    while (--step > 0) {
                        result.add(matrix[x][y++]);
                        total--;
                    }
                    break;
                case 2://x++
                    step = xLen - x * 2;
                    while (--step > 0) {
                        result.add(matrix[x++][y]);
                        total--;
                    }
                    break;
                case 3://y--
                    step = y * 2 - yLen + 2;
                    while (--step > 0) {
                        result.add(matrix[x][y--]);
                        total--;
                    }
                    break;
                case 4://x--
                    step = x * 2 - xLen + 2;
                    while (--step > 0) {
                        result.add(matrix[x--][y]);
                        total--;
                    }
                    //下一轮起始点,手动调整起始点
                    x++;
                    y++;
                    break;
                default:
                    return result;
            }
            if (total == 1) {
                result.add(matrix[x][y]);
                total = 0;
            }
            direct = direct % 4 + 1;
        }
        return result;
    }
}
