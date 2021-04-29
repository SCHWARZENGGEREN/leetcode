package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/4/8 15:52
 * @Description: 154. 寻找旋转排序数组中的最小值 II
 * <p>
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,5]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 4};
        System.out.println(findMin(nums));
    }

    /**
     * 看题意,似乎不对数组排序也行,那么只要找出nums.min即可
     * 相比于上一题,多了个重复数字
     * 为了提升效率,倒叙查找(针对[0,1,2,0]这种情况)
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int findMin(int[] nums) {
        int min = nums[0];
        if (nums.length > 1 && nums[0] >= nums[nums.length - 1]) {
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] < nums[i - 1]) {
                    min = Math.min(min, nums[i]);
                    break;
                }
            }
        }
        return min;
    }

    //TODO 二分法查询
    public static int findMin1(int[] nums) {

        return nums[0];
    }
}
