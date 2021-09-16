package com.example.leetcode.solutions;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/8/23 10:48
 * @Description: 1646. 获取生成数组中的最大值
 * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 * <p>
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 * 返回生成数组 nums 中的 最大 值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 7
 * 输出：3
 * 解释：根据规则：
 * nums[0] = 0
 * nums[1] = 1
 * nums[(1 * 2) = 2] = nums[1] = 1
 * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 * nums[(2 * 2) = 4] = nums[2] = 1
 * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 * nums[(3 * 2) = 6] = nums[3] = 2
 * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：1
 * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
 * 示例 3：
 * <p>
 * 输入：n = 3
 * 输出：2
 * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 100
 */
public class GetMaximumInGeneratedArray {

    public static void main(String[] args) {
//        System.out.println(getMaximumGenerated(7));
        System.out.println(getMaximumGenerated(15));
    }

    /**
     * 当idx为偶数时,num[idx] = nums[idx/2]
     * 当idx为奇数时,num[idx] = nums[idx/2] + nums[idx/2] + nums[idx - idx/2]
     * 根据题意,除了迭代出数组外,还要注意,最后一个下标为奇数的数字不一定是最大的,因为所有2^n位数字都是1,因此如果某奇数位数字有2^n"血统",就有可能不是最大的,所以最简单的还是每次记录最大值
     *
     * @param n
     * @return
     */
    public static int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        int[] nums = new int[n + 1];
        nums[1] = 1;

        int subIdx = 0, maxNum = 1;
        for (int i = 2; i <= n; i++) {
            subIdx = i / 2;
            nums[i] = i % 2 == 0 ? nums[subIdx] : (nums[subIdx] + nums[i - subIdx]);
            maxNum = Math.max(maxNum, nums[i]);
        }
        System.out.println(Arrays.toString(nums));
        return maxNum;
    }

    /**
     * 观察数字规律,发现: 1,奇数位数字不小于偶数位
     *                 2,如果奇数位角标包含2^n"血统",那么它可能会小于上一个奇数位数字
     * @param n
     * @return
     */
    public static int getMaximumGenerated1(int n) {
        if (n == 0) return 0;
        int[] nums = new int[n + 1];
        nums[1] = 1;

        int subIdx = 0, maxNum = 1;
        return maxNum;
    }
}
