package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/5/27 14:32
 * @Description: 面试题 17.16. 按摩师
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，
 * 因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例 1：
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * <p>
 * 示例 2：
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * <p>
 * 示例 3：
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 */
public class TheMasseuseLcci {

    public static void main(String[] args) {
        int[] nums = {2, 1, 4, 5, 3, 1, 1, 3};
        int[] nums1 = {2, 1, 1, 2};
        int[] nums2 = {1, 7, 9, 4};
        System.out.println(massage(nums));
        System.out.println(massage(nums1));
        System.out.println(massage(nums2));
    }

    /**
     * 动态规划:使用状态转移
     * 如果今天不休息,那么昨天一定休息,那么sum[i] = nums[i]+sum[i-2]
     * 如果今天休息,那么今天没有累计,直接取昨天的
     * 最后,max =(sum[i-1],nums[i]+sum[i-2])
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int massage(int[] nums) {
        if (nums.length < 2) return nums.length > 0 ? nums[0] : 0;

        int i = 2;
        int[] sums = new int[nums.length];//按照状态转移规则计算好前两天的
        sums[0] = nums[0];
        sums[1] = Math.max(sums[0], nums[1]);
        while (i < nums.length) {
            sums[i] = Math.max(
                    sums[i - 1],
                    nums[i] + sums[i - 2]
            );
            i++;
        }
        return sums[nums.length - 1];
    }
}
