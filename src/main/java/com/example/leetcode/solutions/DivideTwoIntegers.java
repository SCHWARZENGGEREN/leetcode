package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2019/11/15 10:50
 * @Description: 给定两个整数，被除数 dividend 和除数 divisor。
 * 将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * <p>
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 */
@Unsettled
public class DivideTwoIntegers {

    public static void main(String[] args) {
        int dividend, divisor;

        dividend = 30;
        divisor = -4;

        dividend = 1857849;
        divisor = -4232;

        dividend = 2147483647;
        divisor = -3;

        dividend = -2147483648;
        divisor = 2;

        System.out.println(dividend / divisor);
        System.out.println(divide1(dividend, divisor));
    }

    /**
     * 使用位运算符号
     *
     * @param dividend
     * @param divisor
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.B)
    public static int divide1(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend > Integer.MIN_VALUE ? -dividend : Integer.MAX_VALUE;

        boolean hasSign = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        //为防止越界,统一用负数
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int tempDividend = dividend,
                tempDivisor = divisor,
                tempMulti = 1,
                res = 0;
        //以 1,2,4,8... 等倍率靠近
        while (tempDivisor >= tempDividend) {
            if (tempDivisor <= tempDividend - tempDivisor) {
                //reset
                res += tempMulti;
                tempMulti = 1;
                tempDividend = tempDividend - tempDivisor;
                tempDivisor = divisor;
            } else {
                tempDivisor += tempDivisor;
                tempMulti += tempMulti;
            }
        }

        return hasSign ? -res : res;
    }


    private static final int[] LEVELS = {10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    private static int getLevel(int num) {
        int absNum = Math.abs(num);
        int tempIdx = 0;
        while (tempIdx < LEVELS.length) {
            if (absNum > LEVELS[tempIdx]) {
                break;
            }
            tempIdx++;
        }
        return tempIdx;
    }

}
