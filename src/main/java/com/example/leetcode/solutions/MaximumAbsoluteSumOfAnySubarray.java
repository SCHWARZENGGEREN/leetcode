package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

/**
 * @author renxinheng
 * @ClassName MaximumAbsoluteSumOfAnySubarray
 * @createTime 2023/8/8
 * @desc 1749. 任意子数组和的绝对值的最大值
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * <p>
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * <p>
 * abs(x) 定义如下：
 * <p>
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * @see com.example.leetcode.solutions.MaximumSubarray
 */
public class MaximumAbsoluteSumOfAnySubarray {

    @Unsettled
    public static void main(String[] args) {
        int[] nums = {1,-3,2,3,-4};
        int[] nums1 = {2,-5,1,-4,3,-2};
        System.out.println(maxAbsoluteSum(nums));
        System.out.println(maxAbsoluteSum(nums1));
        System.out.println(maxSubArray(nums, true));
        System.out.println(maxSubArray(nums1, false));
    }

    /**
     * 最大子数组之和
     *
     * @param nums
     * @return
     */
    public static int maxAbsoluteSum(int[] nums) {
        int sum = nums[0], sumTmp;
        int maxAbsSum = Math.abs(sum);
        for (int i = 1; i < nums.length; i++) {
            sumTmp = nums[i] + sum;
            if ((sum > 0 && sumTmp <=0) || sum < 0 && sumTmp >=0 ){
//            }
//            if (!isSameSymbol(sumTmp, sum) || sumTmp == 0) {
                //计算完之后转符号 重新计算
                sum = nums[i];
            }else {
                sum = sumTmp;
            }
            maxAbsSum = Math.max(Math.abs(sum), maxAbsSum);
        }
        return Math.abs(maxAbsSum);
    }

    //是否相同符号
    public static boolean isSameSymbol(int a, int b) {
        return (a > 0 && b > 0) || (a < 0 && b < 0);
    }

    public static int maxSubArray(int[] nums, boolean posOrNeg) {
        int sum = nums[0], maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            if ((posOrNeg &&sum < 0) ||(!posOrNeg &&sum > 0)) {
                //如果sum累计到了负值,则重新开始累计
                sum = 0;
            }
            sum += nums[i];
            maxSum = Math.max(maxSum, Math.abs(sum));
        }
        return maxSum;
    }
}
