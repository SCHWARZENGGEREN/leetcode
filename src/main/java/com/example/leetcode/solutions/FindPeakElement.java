package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/9/15 13:09
 * @Description: 162. 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class FindPeakElement {

    /**
     * 南乡子·登京口北固亭有怀
     * 南宋-辛弃疾
     * 何处望神州?满眼风光北固楼
     * 千古兴亡多少事?悠悠.不尽长江滚滚流
     * 年少万兜鍪,坐断东南战未休
     * 天下英雄谁敌手?曹刘.生子当如孙仲谋
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1};
        int[] nums = {3, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }

    /**
     * 假设从任一点n开始,如果nums[n-1] < nums[n],则n右边一定有峰值;反之则左侧一定有峰值;
     * 为提升效率,直接从mid开始,向左or向右遍历
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.C)
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len >= 3) {
            int mid = len / 2, pos = mid, dir = nums[pos] > nums[pos - 1] ? 1 : -1;
            while ((pos += dir) >= 0 && pos <= len - 1) {
                if (nums[pos] < nums[pos - dir]) break;
            }
            return pos - dir;
        }
        return len == 1 || nums[0] >= nums[1] ? 0 : 1;
    }
}
