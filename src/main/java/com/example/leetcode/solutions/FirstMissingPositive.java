package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/10/23 16:58
 * @Description: 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {-5};
//        System.out.println("S1 " + firstMissingPositive1(nums));
        System.out.println("S2 " + firstMissingPositive2(nums));

        System.exit(0);
    }


    /**
     * 简单粗暴的思路:排序加一一对比
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static int firstMissingPositive1(int[] nums) {
        if (nums.length > 0) {
            Arrays.sort(nums);
            if (nums[0] > 1) return 1;
            int num = 1, last = nums[0] - 1;
            for (int i = 0; i < nums.length; i++) {
                int current = nums[i];
                if (current > 0 && last != current) {
                    if (nums[i] != num) break;
                    last = current;
                    num++;
                }
            }
            return num;
        }
        return 1;
    }

    /**
     * 遍历数组,在排序过程中找出缺少的那个正数
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.SS)
    public static int firstMissingPositive2(int[] nums) {
        int len = nums.length, tmp, lastN = 0;
        if (len == 0) return 1;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (nums[j] < nums[i]) {
                    tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            if (i == 0) {
                if (nums[i] > 1) return 1;
            } else {
                //判断i和i-1处值是否连续
                if (nums[i] - lastN > 1) {
                    return lastN + 1;
                }
            }
            //将所有负数视为0
            lastN = Math.max(nums[i], 0);
        }
        return lastN + 1;
    }

}
