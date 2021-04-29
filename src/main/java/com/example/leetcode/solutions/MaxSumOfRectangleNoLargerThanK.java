package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2021/4/22 16:42
 * @Description: 363. 矩形区域不超过 K 的最大数值和
 * <p>
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * <p>
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSumOfRectangleNoLargerThanK {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1},
                {0, -2, 3}
        };
        int k = 2;
        System.out.println(maxSumSubmatrix(matrix, k));

    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {

        return 0;
    }
}
