package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName SubtractTheProductAndSumOfDigitsOfAnInteger
 * @createTime 2023/8/9
 * @desc 1281. 整数的各位积和之差
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * <p>
 * 示例 1：
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * <p>
 * 示例 2：
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 * <p>
 * 提示：
 * 1 <= n <= 10^5
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {

    public static void main(String[] args) {
        int n = 234;
        System.out.println(subtractProductAndSum(n));
    }

    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int subtractProductAndSum(int n) {
        int sum = 0;
        int multiply = 1;
        int dig = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            sum += dig = c - '0';
            multiply *= dig;
        }
        return multiply - sum;
    }

}
