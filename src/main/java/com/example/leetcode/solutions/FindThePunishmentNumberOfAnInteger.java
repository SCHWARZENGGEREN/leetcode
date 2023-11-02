package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName FindThePunishmentNumberOfAnInteger
 * @createTime 2023/10/25
 * @desc 2698. 求一个整数的惩罚数
 * 给你一个正整数 n ，请你返回 n 的 惩罚数 。
 * n 的 惩罚数 定义为所有满足以下条件 i 的数的平方和：
 * 1 <= i <= n
 * i * i 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 i 。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：182
 * 解释：总共有 3 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * 因此，10 的惩罚数为 1 + 81 + 100 = 182
 * <p>
 * 示例 2：
 * 输入：n = 37
 * 输出：1478
 * 解释：总共有 4 个整数 i 满足要求：
 * - 1 ，因为 1 * 1 = 1
 * - 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
 * - 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
 * - 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
 * 因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
 * <p>
 * 提示：
 * 1 <= n <= 1000
 */
public class FindThePunishmentNumberOfAnInteger {

    public static final int[] PUNISHMENT_NUMS = {1, 9, 10, 36, 45, 55, 82, 91, 99, 100, 235, 297, 369, 370, 379, 414, 657, 675, 703, 756, 792, 909, 918, 945, 964, 990, 991, 999, 1000};
    public static final int[] PUNISHMENT_SQUART_NUMS = {1, 81, 100, 1296, 2025, 3025, 6724, 8281, 9801, 10000, 55225, 88209, 136161, 136900, 143641, 171396, 431649, 455625, 494209, 571536, 627264, 826281, 842724, 893025, 929296, 980100, 982081, 998001, 1000000};

    public static void main(String[] args) {
        System.out.println(punishmentNumber(37));
    }

    /**
     * 符合特征的数字是固定的,直接穷举!
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.FULL,memory = Score.S.SS)
    public static int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 0; i < PUNISHMENT_NUMS.length; i++) {
            if (PUNISHMENT_NUMS[i] <= n){
                sum += PUNISHMENT_SQUART_NUMS[i];
            }
        }
        return sum;
    }
}
