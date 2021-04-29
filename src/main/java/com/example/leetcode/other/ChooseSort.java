package com.example.leetcode.other;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/9/9 14:12
 * @Description:
 */
public class ChooseSort {

    public static void main(String[] args) {
        int[] nums = {9, 2, 4, -1, 2, -5, 3};
        int[] nums1 = {4, 2, 4, 3, 0, 1, 3, 2};
        sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 选择排序法
     * 先选取第一个元素遍历数组,如果遇到比它小的元素则与之互换,一圈下来后第一个位置的元素一定是整个数组最小的;
     * 然后选取第二个元素进行遍历
     * 以升序为例:
     */
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int numI = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int numJ = nums[j];
                if (numJ < numI) {
                    nums[j] = numI;
                    numI = (nums[i] = numJ);
                }
            }
        }
    }
}
