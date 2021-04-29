package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/11/11 13:31
 * @Description: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1};
        int target = 2;
        System.out.println(searchInsert2(nums, target));
    }

    /**
     * 二分法递归
     * <p>
     * 速度较快,但是内存消耗多
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int searchInsert1(int[] nums, int target) {
        return binarySearchTarget(nums, 0, nums.length - 1, target);
    }

    /**
     * 使用二分法查询
     *
     * @return
     */
    public static int binarySearchTarget(int[] nums, int start, int end, int target) {
        int mid = (start + end) / 2;
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        if (nums[mid] == target) return mid;
        if (mid == start) return nums[start] >= target ? start : nums[end] >= target ? end : end + 1;

        if (nums[mid] > target) {
            end = mid;
        } else {
            start = mid;
        }

        return binarySearchTarget(nums, start, end, target);
    }

    /**
     * 优化:将递归改为遍历
     * 耗时不变,但内存消耗依然没有提升
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int searchInsert2(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;
            if (nums[mid] == target) return mid;
            if (mid == start) return nums[start] >= target ? start : nums[end] >= target ? end : end + 1;

            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return 0;
    }

    /**
     * 暴力查找法,直接遍历数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert3(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
            target++;
        }
        return 0;
    }
}
