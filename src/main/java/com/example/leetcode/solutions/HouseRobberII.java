package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/4/15 10:31
 * @Description: 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *  
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRobberII {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        int[] nums1 = {2, 3, 2};

//        nums = nums1;
        System.out.println(rob(nums));
    }

    /**
     * 在原本基础上,将结果分为双列:一种从1开始到len-1;一种从0开始截止到len-2
     * 即max = max(rob(nums[1,len-1]),rob(nums[0,len-2]))
     *
     * @param nums
     * @return
     * @see HouseRobber#rob1(int[])
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(
                robb(Arrays.copyOfRange(nums, 0, len - 1)),
                robb(Arrays.copyOfRange(nums, 1, len))
        );
    }

    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int robb(int[] nums) {
        //正向推算递归
        int prepre = 0, pre = nums[0], curr;
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(pre, prepre + nums[i]);
            prepre = pre;
            pre = curr;
        }
        return pre;
    }


}
