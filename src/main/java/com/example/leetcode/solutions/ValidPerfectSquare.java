package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/11/4 10:44
 * @Description: 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * <p>
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 * <p>
 * 提示：
 * 1 <= num <= 2^31 - 1
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(25));
        System.out.println(isPerfectSquare(99));
    }

    /**
     * 1 2 3  4  5 6  7  8  9
     * 1 3 5  7  9 11 13 15 17
     * 1 4 9 16 25 36 49 64 81
     * 任何平方数都满足 x = 1+3+5+7...+(2n-1)
     *
     * @param num
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.D)
    public static boolean isPerfectSquare(int num) {
        //依次减去奇数,减到0说明是平方数
        int sub = 1;
        while (num > 0) {
            num -= sub;
            sub += 2;
        }
        return num == 0;
    }
}
