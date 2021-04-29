package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

/**
 * @Auther: Rxh
 * @Date: 2019/8/26 15:26
 * @Description: 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。且数组顺序均为升序
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] ints1 = {1, 2};
        int[] ints2 = {3, 4};

        System.out.println(findMedianSortedArrays(ints1, ints2));
    }

    /**
     * 对于两个有序数组,可以先将其拼凑成一个不重复的有序数组,然后计算中位数(暴力)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    @Unsettled
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, newLen = len1 + len2, subNewLen = newLen / 2, idx = 0, idx1 = 0, idx2 = 0;
        int[] merageArr = new int[newLen];
        boolean notEpt1 = len1 > 0, notEpt2 = len2 > 0;
        int num1, num2;
        if (!notEpt1 && !notEpt2) {
            return 0d;
        } else if (notEpt1 && !notEpt2) {
            merageArr = nums1;
        } else if (!notEpt1 && notEpt2) {
            merageArr = nums2;
        } else {
            boolean _1Ept, _2Ept;
            while (subNewLen >= idx && (idx1 < len1 || idx2 < len2)) {
                _1Ept = idx1 >= len1;
                _2Ept = idx2 >= len2;
                if (!_1Ept && !_2Ept) {
                    num1 = nums1[idx1];
                    num2 = nums2[idx2];
                    if (num1 <= num2) {
                        merageArr[idx++] = num1;
                        idx1++;
                    } else {
                        merageArr[idx++] = num2;
                        idx2++;
                    }
                } else if (!_1Ept && _2Ept) {
                    merageArr[idx++] = nums1[idx1++];
                } else {
                    merageArr[idx++] = nums2[idx2++];
                }
            }
        }
        return newLen % 2 == 0 ? (double) (merageArr[subNewLen - 1] + merageArr[subNewLen]) / 2 : merageArr[subNewLen];
    }
}
