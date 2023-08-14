package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Rxh
 * @Date: 2022/2/9 10:09
 * @Description: 2006. 差的绝对值为 K 的数对数目
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 * <p>
 * |x| 的值定义为：
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * <p>
 * 示例 2：
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 * <p>
 * 示例 3：
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为 2 的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 */
public class CountNumberOfPairsWithAbsoluteDifferenceK {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 4};
        int k = 2;
        System.out.println(
                countKDifference(
                        nums, k
                )
        );
    }

    /**
     * 统计数组中差值为k的数对数量
     *
     * @param nums
     * @param k
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.D)
    public static int countKDifference(int[] nums, int k) {
        int len = nums.length, res = 0, diff;
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if ((diff = nums[i] - nums[j]) == k || diff == -k) res++;
                }
            }
        }
        return res;
    }
}
