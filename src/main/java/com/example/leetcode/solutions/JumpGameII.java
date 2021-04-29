package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2020/1/17 16:34
 * @Description: 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 */
public class JumpGameII {

    public static void main(String[] args) {
        int[] nums =
//                {1, 1, 2, 1, 1}
//                {2, 1}
                {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3,1,1,1,1}
//                {4, 2, 3, 1, 1, 3, 2, 1, 3, 1}
                ;
        System.out.println(jump(nums));
    }

    /**
     * 贪心算法:将一个问题分成多个局部问题,每个局部问题都寻求最优解
     * 正向遍历数组,每到一个点,找寻其跳跃范围内能走最远的下一个节点
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    private static int jump(int[] nums) {
        int steps = 0, len = nums.length,
                i = 0, j = i + 1,
                end = i + nums[i], nextEnd = end;//当前区间节点以及下个区间节点
        while (i < len && j < len) {//遍历角标:如果子角标已经超出范围,终止遍历
            j = i + 1;//子角标初始化
            while (j < len && j <= end) {//找寻下个最远的节点
                if (nums[j] > 0 && j + nums[j] >= nextEnd) {
                    nextEnd = j + nums[j];
                    i = j;
                }
                j++;
            }
            steps++;
            end = nextEnd;//节点右移
        }
        return steps;
    }


}
