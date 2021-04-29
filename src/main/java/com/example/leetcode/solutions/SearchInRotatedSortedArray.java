package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/11/12 15:39
 * @Description: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums =
//                {4, 5, 6, 7, 0, 1, 2};
                {3, 1};
        int target = 0;
        System.out.println(search(nums, target));
    }

    /**
     * 使用二分法,不断切分数组,如果切出来的数组仍是无序的,就再次切分
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.S)
    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len > 0) {
            int start = 0, end = len - 1, mid;
            if (len == 1) return nums[0] == target ? 0 : -1;
            while (start <= end) {
                if (nums[start] < nums[end]) {
                    if (target < nums[start] && target > nums[end]) return -1;
                    return binarySearch(nums, target, start, end);
                }
                //数组无序 继续切分并寻找有序部分
                mid = start + (end - start) / 2;
                if (nums[mid] == target) return mid;
                if (nums[mid] > nums[start]) {
                    //左侧有序
                    if (target >= nums[start] && target <= nums[mid]) {
                        return binarySearch(nums, target, start, mid);
                    }
                    start = mid + 1;
                } else if (nums[mid] < nums[start]) {
                    //右侧有序
                    if (target <= nums[end] && target >= nums[mid]) {
                        return binarySearch(nums, target, mid, end);
                    }
                    end = mid - 1;
                }
                if (mid == start) start++;
            }
        }
        return -1;
    }

    /**
     * 二分法查找
     *
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    private static int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end) return -1;
        int mid = start + (end - start) / 2;//区间开放
        if (nums[mid] == target) return mid;
        if (nums[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return binarySearch(nums, target, start, end);
    }
}
