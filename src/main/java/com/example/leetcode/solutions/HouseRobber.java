package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/4/15 13:39
 * @Description: 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        int[] nums1 = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240};
//        System.out.println(rob(nums1));
        System.out.println(rob1(nums1));
    }

    /**
     * 使用动态规划:每个节点尝试寻找最优解
     * 假设在第curr_idx = n,那么存在两种情况:
     * 1,n-1处被偷了,那么n处就会跳过,f(n) = f(n-1);
     * 2,n-1处没被偷,那么f(n) = f(n-1)+nums[n] = f(n-2)+nums[n]
     * 那么max=Max(f(n-1),f(n-2)+nums[n])
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;

        return getAddedNums(nums, nums.length - 1);
    }

    @Score(time = Score.S.OT, memory = Score.S.NONE)
    private static int getAddedNums(int[] nums, int idx) {
        if (idx < 0) return 0;
        if (idx == 0) return nums[0];
//        if (idx == 1) return Math.max(nums[0], nums[1]);
        return Math.max(
                getAddedNums(nums, idx - 1),
                getAddedNums(nums, idx - 2) + nums[idx]
        );
    }

    /**
     * 递归超时,改用交替索引遍历:
     * 每个节点计算只需要知道pre和pre-pre节点即可
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int rob1(int[] nums) {
        if (nums.length == 0) return 0;
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
