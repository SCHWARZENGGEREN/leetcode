package com.example.leetcode.solutions;


import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2020/1/16 15:22
 * @Description: 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 *
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * https://leetcode-cn.com/problems/jump-game
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] nums =
//      {
//                100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0, 0
//      };
//        {3,0,8,2,0,0,1};
                {2, 0, 699, 9, 8, 4, 5, 0, 8, 9, 1, 2, 9, 6, 8, 8, 0, 6, 3, 1, 2, 2, 1, 2, 6, 5, 3, 1, 2, 2, 6, 4, 2, 4, 3, 0, 0, 0, 3, 8, 2, 4, 0, 1, 2, 0, 1, 4, 6, 5, 8, 0, 7, 9, 3, 4, 6, 6, 5, 8, 9, 3, 4, 3, 7, 0, 4, 9, 0, 9, 8, 4, 3, 0, 7, 7, 1, 9, 1, 9, 4, 9, 0, 1, 9, 5, 7, 7, 1, 5, 8, 2, 8, 2, 6, 8, 2, 2, 7, 5, 1, 7, 9, 6};
        System.out.println(canJump3(nums));
    }


    /**
     * 大佬的思路:
     * 从第一个点开始,在其步数范围内前进,每走到一个点记录其能行进的最远距离;
     * 如果到达某个点所需要的步数大于这个点之前的最大行进距离,那么说明无论如何也到不了这个点;如果能走到终点,则说明至少有一条路径
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.A)
    public static boolean canJump3(int[] nums) {
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (longest < i) return false;
            longest = Math.max(longest, i + nums[i]);
            if (longest >= nums.length - 1) return true;
        }
        return true;
    }

    public static boolean canJump2(int[] nums) {
        if (nums.length == 1) return true;
        boolean[] canJumpPoint = countJumpPoint(nums);

        if (canJumpPoint[nums.length]) {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (canJumpPoint[i] && jump2(nums, 0, i)) return true;
            }
        }

        return false;
    }

    /**
     * 逆推法,逆序遍历数组,如果改点能跳到终点,则往遍历,如果第一个点刚好是初始点,则说明该路径可行
     * TODO 优化了回溯流程,提交依然耗时巨大,险些超时,算你狠哦,只能放弃回溯了
     *
     * @param nums
     * @param startIdx
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    private static boolean jump2(int[] nums, int startIdx, int destIdx) {
        if (startIdx == destIdx) {
            System.out.println(startIdx);
            return true;
        }
        if (startIdx < destIdx) {
            for (int i = 1; i <= nums[startIdx]; i++) {
                if (jump2(nums, startIdx + i, destIdx)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回一个布尔数组,记录每个点是否能直接跳到终点,此外,在boolean[len]记录整个数组是否至少有一个点能直接跳往终点
     *
     * @param nums
     * @return
     */
    private static boolean[] countJumpPoint(int[] nums) {
        int length = nums.length;
        boolean[] count = new boolean[length + 1];

        for (int i = length - 2; i >= 0; i--) {
            if (nums[i] > 0 && nums[i] >= length - i - 1) {
                count[i] = count[length] ? count[length] : (count[length] = true);
            }
        }

        return count;
    }

    public static boolean canJump1(int[] nums) {
        if (nums.length == 1) return true;

        return jump1(nums, 0);
    }


    /**
     * 使用回溯找出可能的路径,如果找不到则返回false
     * 这里应该使用BFS广度优先规则搜索,
     * <p>
     * TODO 由于使用深度优先策略无法很好的应对大量数据,可以改成广度优先策略试试
     *
     * @return
     */
    private static boolean jump1(int[] nums, int idx) {
        if (idx < nums.length) {
            if (idx == nums.length - 1 || nums[idx] == (nums.length - 1 - idx)) return true;
            int i = nums[idx];
            while (i >= 1) {
                if (i < nums.length - idx && jump1(nums, idx + i)) {
                    return true;
                }
                i--;
            }
        }
        return false;
    }
}
