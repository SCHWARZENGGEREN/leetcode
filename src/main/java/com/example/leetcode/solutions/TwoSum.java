package com.example.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2019/8/21 10:09
 * @Description:
 */
public class TwoSum {

    public static void main(String[] args) {

    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     */
    private static int[] twoSum(int[] nums, int target) {
        //使用减法
        StringBuilder num1Idxs = new StringBuilder(",");//记录角标
        StringBuilder num2s = new StringBuilder(",");//记录目标数字
        int[] res = new int[2];


        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (num2s.indexOf("," + n + ",") >= 0) {
                String[] split1 = num2s.toString().split(",");
                int targetIdx = 0;
                for (int j = 0; j < split1.length; j++) {
                    if (String.valueOf(n).equals(split1[j])) {
                        targetIdx = j;
                        res[1] = i;
                        break;
                    }
                }
                res[0] = Integer.valueOf(num1Idxs.toString().split(",")[targetIdx]);

            } else {
                num1Idxs.append(i).append(",");
                num2s.append(target - n).append(",");
            }
        }

//        System.out.println(res[0]);
//        System.out.println(res[1]);
        return res;
    }

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


//        System.out.println(res[0]);
//        System.out.println(res[1]);
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
