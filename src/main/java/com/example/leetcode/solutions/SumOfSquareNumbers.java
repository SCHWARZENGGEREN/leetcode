package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/4/28 10:12
 * @Description: 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 * <p>
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * <p>
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= c <= 2^31 - 1
 */
public class SumOfSquareNumbers {


    public static void main(String[] args) {
//        Util.judgeInvokeTime(object -> System.out.println(judgeSquareSum(11108890)));
        Util.judgeInvokeTime(object -> System.out.println(judgeSquareSum1(3)));
    }

    /**
     * 判断依据: c本身是某个数字的平方or c = a^2+b^2 ,其中a可能等于b
     *
     * @param c
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static boolean judgeSquareSum(int c) {
        if (c >= 0) {
            List<Integer> cache = new ArrayList<>();
            double sqrt = Math.sqrt(c);
            if (isInteger(sqrt)) return true;
            int i = (int) sqrt;
            while (i >= 0) {
                if (cache.contains(i)) break;
                double destNum = Math.sqrt(c - i * i);
                if (isInteger(destNum)) return true;
                cache.add((int) destNum);
                i--;
            }
        }

        return false;
    }

    /**
     * 双指针法
     *
     * @param c
     * @return
     */
    @Score(time = Score.S.SS, memory = Score.S.D)
    public static boolean judgeSquareSum1(int c) {
        if (c >= 0) {
            double sqrt = Math.sqrt(c);
            if (isInteger(sqrt)) return true;
            int i = (int) sqrt;
            int j = 1;
            while (i >= j) {
                int add = i * i + j * j;
                if (add < c) {
                    j++;
                } else if (add > c) {
                    i--;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isInteger(double dou) {
        return dou % 1 == 0;
    }

}
