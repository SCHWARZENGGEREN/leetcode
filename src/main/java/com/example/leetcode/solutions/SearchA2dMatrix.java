package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.other.BinarySearch;

/**
 * @Auther: Rxh
 * @Date: 2021/3/30 13:55
 * @Description: 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *  
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchA2dMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }

    /**
     * 先找出目标一维数组,然后用二分法
     *
     * @param matrix
     * @param target
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static boolean searchMatrix(int[][] matrix, int target) {
        int y = matrix.length, x = matrix[0].length;
        int[] destNums = null;
        for (int j = 0; j < y; j++) {
            if (matrix[j][0] == target) {
                return true;
            } else if (matrix[j][0] < target) {
                if (j == y - 1 || matrix[j][x - 1] >= target) {
                    destNums = matrix[j];
                    break;
                }
            }
        }
        //二分法查询
        return BinarySearch.binarySearch1(destNums, 0) >= 0;
    }

    private static boolean binarySearch(int[] nums, int start, int end, int target) {
        if (target >= nums[start] && target <= nums[end]) {
            if (nums[start] == target || nums[end] == target) return true;
            if (end <= start + 1) return false;
            int mid = (start + end) / 2;

            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
            return binarySearch(nums, start, end, target);
        }
        return false;
    }
}
