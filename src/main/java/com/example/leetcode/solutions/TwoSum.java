package com.example.leetcode.solutions;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2019/8/21 10:09
 * @Description: 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *  示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * 只会存在一个有效答案
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum1(nums, target)));
    }

    /**
     * 方式1,暴力破解
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];

        for(int i = 0;i<nums.length;i++){
            int m = nums[i];
            for(int j = 0;j<nums.length;j++){
                int n = nums[j];
                if ((n+m) == target){
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
    private static int[] twoSum3(int[] nums, int target) {
        int[] res = new int[2];

        List<Integer> aIdxs = new ArrayList<>();
        List<Integer> bNums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (bNums.contains(n)) {
                int indexOf = bNums.indexOf(n);
                res[0] = aIdxs.get(indexOf);
                res[1] = i;
            } else {
                aIdxs.add(i);
                bNums.add(target-n);
            }
        }

        return res;
    }
    private static int[] twoSum4(int[] nums, int target) {
        int[] res = new int[2];

        Map<Integer,Integer> num2_num1IdxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num2_num1IdxMap.containsKey(num)){
                res[0] = num2_num1IdxMap.get(num);
                res[1] = i;
                break;
            }else {
                int subNum = target - num;
                if (!num2_num1IdxMap.containsKey(subNum)){
                    num2_num1IdxMap.put(subNum,i);
                }
            }
        }
        return res;
    }
}
