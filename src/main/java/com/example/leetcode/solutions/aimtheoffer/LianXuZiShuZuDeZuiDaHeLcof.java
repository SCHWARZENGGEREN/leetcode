package com.example.leetcode.solutions.aimtheoffer;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName LianXuZiShuZuDeZuiDaHeLcof
 * @createTime 2023/8/11
 * @desc 剑指 Offer 42. 连续子数组的最大和
 * 简单
 * 714
 * 相关企业
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * @see com.example.leetcode.solutions.MaximumSubarray
 */
public class LianXuZiShuZuDeZuiDaHeLcof {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 遍历求和,如果累计求和<0,则没必要计入总和,重新开始计算
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS)
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = maxSum;
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
