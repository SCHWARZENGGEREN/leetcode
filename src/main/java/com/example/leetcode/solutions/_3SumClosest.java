package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/9/10 11:15
 * @Description: 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class _3SumClosest {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        int target = 82;
        System.out.println(S1.threeSumClosest(nums, target));
    }

    static class S1 {
        /**
         * 套用三数之和思路,使用一个角标和两个移动角标进行遍历数组
         *
         * @param nums
         * @param target
         * @return
         */
        @Score(time = Score.S.SS, memory = Score.S.S)
        static int threeSumClosest(int[] nums, int target) {
            int length = nums.length,
                    closet = nums[0] + nums[1] + nums[2],
                    closetDiff = Math.abs(target - closet);
            Arrays.sort(nums);
            for (int i = 0; i < length - 2; i++) {
                int iVal = nums[i];
                //TODO 懒人校验
                int j = i + 1, k = length - 1;
                while (j < k) {
                    int jVal = nums[j];
                    int kVal = nums[k];
                    int sum = iVal + jVal + kVal;
                    if (target == sum) {
                        return sum;
                    } else if (target > sum) {
                        j++;
                    } else {
                        k--;
                    }

                    int diff = Math.abs(target - sum);
                    if (diff < closetDiff) {
                        closetDiff = diff;
                        closet = sum;
                    }
                }
            }
            return closet;
        }
    }
}
