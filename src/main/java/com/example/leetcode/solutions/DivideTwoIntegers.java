package com.example.leetcode.solutions;

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
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 */
@Unsettled
public class DivideTwoIntegers {

    public static void main(String[] args) {
        int dividend, divisor;

        dividend = 1857849;
        divisor = -4232;

        dividend = 2147483647;
        divisor = 3;

//        System.out.println(dividend / divisor);
//        System.out.println(divide1(dividend, divisor));
        System.out.println(3 / 2);
    }

    /**
     * 使用位运算符号
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide1(int dividend, int divisor) {
        //只有被除数为-1时才会出现越界
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (dividend == divisor) return 1;
        if (dividend + divisor == 0) return -1;
        if (divisor == Integer.MIN_VALUE && dividend > 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MIN_VALUE;

        boolean ddPos = dividend > 0, diPos = divisor > 0, diffSymbol = ddPos ^ diPos;

        if (Math.abs(dividend) < Math.abs(divisor)) return 0;

        Map<Integer, Integer> powerCache = new HashMap<>();//缓存数字的倍数
        int drift, multiRes,//位移数 & 乘算结果
                absDivisor = Math.abs(divisor), absDividend = Math.abs(dividend),
                diff = absDividend,//差距
                res = 0;
        while (diff > absDivisor) {
            drift = 0;
            while ((multiRes = get2Power(absDivisor, ++drift, powerCache)) < diff && multiRes > 0) {}
            if (multiRes > diff || multiRes < 0) {
                multiRes = powerCache.get(drift - 1);
                drift--;
            }
            diff -= multiRes;
            res += 2 << (drift - 1);
            //如果结果<2*divisor 则+1终止
            if (diff > absDivisor && diff < powerCache.get(1)) {
                res++;
                break;
            }
        }
        return ddPos ^ diPos ? res : -res;
    }

    private static int get2Power(int num, int drift, Map<Integer, Integer> powerCache) {
        Integer cache = powerCache.get(drift);
        if (cache == null) {
            cache = num << drift;
            powerCache.put(drift, cache);
        }
        return cache;
    }


    /**
     * 十倍界王拳!
     * 为了避免超出int空间,只计算到Integer.MAX以下最大值
     * 如果超出范围,返回-1
     *
     * @param base  >=0
     * @param power 乘数
     * @return
     */
    private static int multi(int base, int power) {
        if (base == 0) return 0;
        if (base == 1) return power;

        int tempRes = base;
        int tempIdx = 0;
        while (tempIdx++ < power) {
            if (Integer.MAX_VALUE - tempRes > base) {
                tempRes += base;
            } else {
                //超出界限
                tempRes = -1;
                break;
            }
        }
        return tempRes;
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
