package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * 357. 统计各位数字都不同的数字个数
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 * <p>
 * 提示：
 * 0 <= n <= 8
 */
public class CountNumbersWithUniqueDigits {

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(4));
    }

    /**
     * 数学题,排列组合
     * f(0)=1
     * f(1)=9+f(0)
     * f(2)=9*9+f(1)
     * f(3)=9*9*8+f(2)
     * f(4)=9*9*8*7+f(3)
     * f(5)=9*9*8*7*6+f(4)
     * f(6)=9*9*8*7*6*5+f(5)
     * f(7)=9*9*8*7*6*5*4+f(6)
     * f(8)=9*9*8*7*6*5*4*3+f(7)
     * f(9)=9*9*8*7*6*5*4*3*2+f(8)
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.FULL, memory = Score.S.C, way = Score.WAY.REFER)
    public static int countNumbersWithUniqueDigits(int n) {
        if (n <=0) return 1;
        int accumulate = 9;
        int i = n;
        while (i > 1) {
            accumulate *= (11 - i);
            i--;
        }
        return countNum(n-1, accumulate) + accumulate;
    }

    private static int countNum(int x, int accumulate) {
        if (x == 0) return 1;
        accumulate /= (10 - x);
        return countNum(x - 1, accumulate) + accumulate;
    }

    //排列公式
    public static int countNumbersWithUniqueDigits1(int n) {
        int ans = 0;
        switch(n) {
            case 8: ans += 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3;
            case 7: ans += 9 * 9 * 8 * 7 * 6 * 5 * 4;
            case 6: ans += 9 * 9 * 8 * 7 * 6 * 5;
            case 5: ans += 9 * 9 * 8 * 7 * 6;
            case 4: ans += 9 * 9 * 8 * 7;
            case 3: ans += 9 * 9 * 8;
            case 2: ans += 9 * 9;
            case 1: ans += 9;
            case 0: ans += 1;
        }
        return ans;
    }
}