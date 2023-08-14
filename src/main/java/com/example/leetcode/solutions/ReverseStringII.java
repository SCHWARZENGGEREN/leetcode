package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/8/20 10:09
 * @Description: 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * <p>
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由小写英文组成
 * 1 <= k <= 10^4
 */
public class ReverseStringII {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStr(s, k));

        s = "abcd";
        System.out.println(reverseStr(s, k));
    }

    @Score(time = Score.S.SS, memory = Score.S.SS)
    public static String reverseStr(String s, int k) {
        if (k == 1) return s;

        int pos = 0;
        StringBuilder sb = new StringBuilder(s);
        while (pos < s.length()) {
            reverse(sb, pos, Math.min(s.length() - 1, pos + k - 1));
            pos += k * 2;
        }

        return sb.toString();
    }

    private static void reverse(StringBuilder s, int l, int r) {
        while (l < r) {
            char left = s.charAt(l);
            s.setCharAt(l, s.charAt(r));
            s.setCharAt(r, left);

            l++;
            r--;
        }
    }
}
