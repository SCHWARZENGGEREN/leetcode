package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import org.apache.shiro.crypto.hash.Hash;
import sun.misc.Queue;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Rxh
 * @Date: 2021/6/2 10:19
 * @Description: 523. 连续的子数组和
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * <p>
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 * <p>
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
        System.out.println(checkSubarraySum1(nums, k));
    }

    /**
     * 先计算各个点sum值,假设区间[j,i]满足条件, 那么一定有:sum[i]%k = num[j-1]%k
     * 构建余数表
     *
     * @param nums
     * @param k
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static boolean checkSubarraySum1(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) return false;
        if (k == 1) return true;
        int[] sums = new int[len];
        sums[0] = nums[0];
        //遍历sum值,如果sum[i]%k出现过,就说明之前那个点到现在这个点之间的数之和是6的倍数
        Set<Integer> remains = new HashSet<>();
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
            remains.add(getSum(sums, i - 2) % k);
            if (remains.contains(sums[i] % k)) {
                System.out.println("第一个符合条件的子数组结束点是: " + i);
                return true;
            }
        }
        return false;
    }

    /**
     * 构建累计值,然后判断
     * 1,使用减法:先拿到sums[i],然后从0开始依次减,直到sums[i-2]
     *
     * @param nums
     * @param k
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static boolean checkSubarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sums[i] = nums[i] + getSum(sums, i - 1);
            if (i > 0 && nums[i] != 0) {
                //从当前点往前遍历
                if (sums[i] % k == 0) {
                    System.out.println("第一个符合条件的子数组是: 0~" + i);
                    return true;
                }
                int j = -1;
                while (j < i - 1) {
                    if ((sums[i] - getSum(sums, j)) % k == 0) {
                        System.out.println("第一个符合条件的子数组是: " + (j + 1) + "~" + i);
                        return true;
                    }
                    j++;
                }
            }
        }
        return false;
    }

    private static int getSum(int[] sums, int idx) {
        return idx >= 0 ? sums[idx] : 0;
    }
}
