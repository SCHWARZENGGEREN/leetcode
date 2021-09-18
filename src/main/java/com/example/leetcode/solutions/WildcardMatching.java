package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2021/9/18 14:49
 * @Description: 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 */
public class WildcardMatching {

    public static void main(String[] args) {
        String s = "acdcb", p = "a*c?b";
        System.out.println(isMatch(s, p));
    }

    /**
     * 使用dp[][]方程记录s和p匹配状态
     * dp[i][j] 表示通配符前i个字符和字符串前j个字符是否匹配
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] matches = new boolean[p.length() + 1][s.length() + 1];
        matches[0][0] = true;
        if (p.startsWith("*")) {
            for (int i = 1; i <= s.length(); i++)
                matches[1][i] = true;

            for (int i = 1; i <= p.length(); i++) {
                for (int j = 1; j <= s.length(); j++) {
                    if (p.charAt(i-1) == '*'){
                        matches[i][j] = true;
                    }
                }
            }
        }

        return matches[p.length()][s.length()];
    }
}
