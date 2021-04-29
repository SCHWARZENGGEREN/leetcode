package com.example.leetcode.other;

/**
 * @Auther: Rxh
 * @Date: 2019/9/9 13:44
 * @Description:
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch1(new int[]{1, 3, 5, 7, 9, 11}, 8));
    }

    /**
     * 二分法搜索数字
     * 前提:数组有序
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int targetIdx = -1;
        if (nums != null && nums.length > 0) {
            int len = nums.length, start = 0, end = len - 1;
            if (nums[end] == nums[start]) {
                return nums[0] == target ? 0 : targetIdx;
            }
            boolean ascend = nums[end] > nums[start];

            int mid = 0;
            while (start < end) {
                mid = (start + end) / 2;
                int midNum = nums[mid];
                if (midNum == target) {
                    targetIdx = mid;
                    break;
                }
                if ((ascend && midNum < target) || (!ascend && midNum > target)) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        return targetIdx;
    }

    public static int binarySearch1(int[] nums, int target) {
        if (nums != null && nums.length > 0) {
            int start = 0, end = nums.length - 1, mid;
            if (target < nums[start] || target > nums[end]) return target;

            while (end > start) {
                mid = (start + end) / 2;
                if (nums[start] == target) return start;
                if (nums[end] == target) return end;
                if (start == mid || end == mid) return -1;

                if (nums[(start + end) / 2] >= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }

        return -1;
    }
}
