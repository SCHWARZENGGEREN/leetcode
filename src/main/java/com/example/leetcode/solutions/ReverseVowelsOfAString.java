package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/8/19 16:13
 * @Description: 345. 反转字符串中的元音字母
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 * <p>
 * 示例 1：
 * 输入：s = "hello"
 * 输出："holle"
 * <p>
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 * <p>
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由 可打印的 ASCII 字符组成
 */
public class ReverseVowelsOfAString {

    public static void main(String[] args) {

        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }

    /**
     * 使用双指针分别从前后遍历,遇到两个元音字母就互换
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.A)
    public static String reverseVowels(String s) {
        if (s.length() > 1) {
            StringBuilder sb = new StringBuilder(s);
            int l = 0, r = s.length() - 1;
            while (l < r) {
                while (l < r && !isVowel(s.charAt(l))) l++;
                while (l < r && !isVowel(s.charAt(r))) r--;

                if (l < r) {
                    change(sb, l, r);
                    l++;
                    r--;
                }
            }

            s = sb.toString();
        }
        return s;
    }

    private static void change(StringBuilder s, int l, int r) {
        char left = s.charAt(l);
        s.setCharAt(l, s.charAt(r));
        s.setCharAt(r, left);
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' ||
                ch == 'e' ||
                ch == 'i' ||
                ch == 'o' ||
                ch == 'u' ||
                ch == 'A' ||
                ch == 'E' ||
                ch == 'I' ||
                ch == 'O' ||
                ch == 'U';
    }
}
