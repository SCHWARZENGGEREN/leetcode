package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/9/23 10:17
 * @Description: 326. 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：n = 45
 * 输出：false
 * <p>
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * <p>
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class PowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(729));
        final int[] _n = {1};
        _3Power.forEach(num -> {
            System.out.println(buildBinaryStr(num) + ": " + num);
            System.out.println(1162261467 | num);
            System.out.println(1162261467 & num);
            System.out.println(1162261467 ^ num);
            _n[0] |= num;
        });
        System.out.println(_n[0]);
    }

    private static String buildBinaryStr(int num) {
        String binaryString = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        sb.append("0")
                .append(StringUtil.getDuplicateStr(31 - binaryString.length(), "0"))
                .append(binaryString);

        String totalStr = sb.toString();

        int idx = 0;
        sb.setLength(0);
        while (idx < totalStr.length()) {
            sb.append(totalStr.charAt(idx));
            if ((++idx) % 4 == 0) {
                sb.append(" ");
            }
        }
        return sb.toString();

    }

    private static List<Integer> _3Power = Arrays.asList(
            1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467
    );

    /**
     * int 范围内3的幂只到3^19,直接枚举
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.A)
    public static boolean isPowerOfThree(int n) {
        return n > 0 && _3Power.contains(n);
    }


    /**
     * 通过数学规律:
     * int范围内3的幂最大是3^19 = 1162261467 = 3*3*3*3*3*3*3.....,
     * 那么对于整型范围内的任意3的幂,都有1162261467%n==0
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SSS)
    public static boolean isPowerOfThree1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
