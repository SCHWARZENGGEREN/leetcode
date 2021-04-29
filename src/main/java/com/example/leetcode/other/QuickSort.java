package com.example.leetcode.other;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/9/9 14:12
 * @Description:
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {9, 2, 4, -1, 2, -5, 3};
        sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 快速排序:
     * 先指定基准数,将其保存在tmp,然后指定左右两个角标,先从先右侧开始轮番移动两个角标
     * 右侧角标向左移动,直到对应数字小于基准数,这时将对应数字赋值给左侧角标位置,此时左右两个角标对应数字相同,右侧角标停止移动
     * 左侧角标向右移动,直到对应数字大于基准数,这时右侧角标对应数字移动转移,相当于该位置是空的,此时将左侧角标对应数字放到这个位置
     * 若左右角标相遇,此时对应位置的数字一定是转移过的,然后将tmp里面的基准数字赋值到此位置,此位置为新的基准位置
     * 上述步骤完成后,以基准位置将数组切成两半(不包含基准位置),并分别迭代重复上述过程直到排完整个数组
     *
     * TODO 数据量较大时,效率极其不稳定
     *
     * @param nums
     * @param start
     * @param end
     */
    public static void sort(int[] nums, int start, int end) {
        int startIdx = start, endIdx = end, tmp = nums[startIdx];
        while (startIdx < endIdx) {
            while (startIdx < endIdx && tmp <= nums[endIdx]) {
                endIdx--;
            }
            nums[startIdx] = nums[endIdx];
            while (startIdx < endIdx && tmp >= nums[startIdx]) {
                startIdx++;
            }
            nums[endIdx] = nums[startIdx];
        }
        int standIdx = startIdx;
        nums[standIdx] = tmp;

        if (standIdx > start + 1) sort(nums, start, standIdx - 1);
        if (standIdx < end - 1) sort(nums, standIdx + 1, end);
    }
}
