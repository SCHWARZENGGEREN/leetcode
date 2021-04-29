package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2020/5/15 11:01
 * @Description: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * <p>
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        int k = 5;
        System.out.println(subarraySum(ints, k));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int length = nums.length;
        if (length > 0) {

        }
        return count;
    }
}
