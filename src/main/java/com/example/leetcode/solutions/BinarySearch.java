package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/9/6 10:20
 * @Description: 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        target = 11;
        System.out.println(search(nums, target));
    }

    /**
     * 二分法查找
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (target >= nums[left] && target <= nums[right]) {
            if (nums[right] == target) return right;
            while (left < right) {
                mid = (left + right) / 2;
                if (nums[left] == target) return left;
                if (nums[mid] == target) return mid;

                if (mid == left) break;//精度丢失

                if (nums[mid] > target) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        return -1;
    }
}
