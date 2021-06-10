package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/5/31 10:13
 * @Description: 342. 4的幂
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * -2^31 <= n <= 2^31 - 1
 */
public class PowerOfFour {

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++)
            System.out.println("i: " + i + " res: " + isPowerOfFour(i));
        for (int i = 1; i < 100; i++)
            System.out.println("i1: " + i + " res: " + isPowerOfFour1(i));
        for (int i = 1; i < 10; i++)
            System.out.println(Integer.toBinaryString(1 << 2 * i));
    }

    /**
     * 如果n可能是负数的话:
     * 先用位运算计算n推算出2的次方数,然后看是不是偶数
     * 先将数字转为绝对值,然后用位运算推算出2的次方数2p,然后应该满足:
     * 2p是偶数,除以2得到4p,如果数字n是负数,4p一定是奇数
     * <p>
     * 如果n是正数,只需要满足对2开方出来的值是偶数
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static boolean isPowerOfFour(int n) {
        int _2P = 0;
        if (n >= 4) {
            while ((n & 1) == 0) {
                n >>= 1;
                _2P++;
            }
        }
        return n == 1 && (_2P & 1) == 0;
    }

    /**
     * 4  = 100
     * 16 = 10000
     * 64 = 1000000
     * 根据二进制的规律
     * @param n
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static boolean isPowerOfFour1(int n) {
        return Integer.toBinaryString(n)
                .replaceAll("00", "")
                .equals("1");
    }

    /**
     * TODO 使用位运算
     * @param n
     * @return
     */
    public static boolean isPowerOfFour2(int n) {
        return false;
    }
}
