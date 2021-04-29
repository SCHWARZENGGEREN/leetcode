package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2020/8/20 14:39
 * @Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        arr = new int[]{-2, 3, -3};
        System.out.println(maxSubArray(arr));
    }

    /**
     * 遍历计算sum,如果在某个点sum减至零以及以下,则重新开始计算,并记录减为零之前的max[sum]
     * 核心思想:当遍历至某个点,之前的累计值<0时,则没必要继续累计,直接开始下一轮的遍历
     * @param nums
     * @return
     */
    @Score(memory = Score.S.SSS, time = Score.S.B)
    public static int maxSubArray(int[] nums) {
        int sum = nums[0], maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                //如果sum累计到了负值,则重新开始累计
                sum = 0;
            }
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
