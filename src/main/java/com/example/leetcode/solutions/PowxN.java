package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2020/1/6 16:06
 * @Description: 实现 pow1(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * <p>
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * <p>
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * <p>
 * https://leetcode-cn.com/problems/powx-n
 */
public class PowxN {

    public static void main(String[] args) {
        System.out.println(myPow(3, -2));
    }

    /**
     * 二分法递归
     * 利用幂的特性
     * x^2=x*x
     * x^5 = (x^2)*(x^2)*x
     * 不断通过递归将幂折半,最终拿到结果
     *
     * @param x
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static double myPow(double x, int n) {
        if (x == 1) return 1;
        if (x == 0) return n == 0 ? 1 : 0;
        if (x == -1) return n % 2 == 0 ? 1 : -1;

        return pow1(x, n);
    }

    public static double pow1(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;

        double half = myPow(x, n / 2);
        double remain = myPow(x, n % 2);
        return half * half * remain;
    }
}
