package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2019/9/4 17:12
 * @Description: 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * <p>
 * 示例 1: 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2: 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3: 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4: 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5: 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(S1.isMatch("", ""));
    }

    static class S1 {
        /**
         * TODO
         *
         * @param s
         * @param p
         * @return
         */
        public static boolean isMatch(String s, String p) {
            if (s != null && p != null) {
                if (s.equals(p)) return true;
                final int ch_point = 46, ch_star = 42;

                char[] chars = s.toCharArray();
                char[] regexps = p.toCharArray();

                int sIdx = 0, sLen = chars.length, pIdx = 0, pLen = regexps.length;
                boolean beforeMatch = true;
                while (sIdx < sLen && pIdx < pLen && beforeMatch) {
                    char pCh = regexps[pIdx];
                    if (pIdx < pLen-1)
                    if (pCh == ch_point) {
                        sIdx++;
                        pIdx++;
                    } else if (pCh == ch_star) {

                    } else {

                    }
                }
            }
            return false;
        }
    }
}
