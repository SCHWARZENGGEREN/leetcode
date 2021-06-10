package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/9/4 17:12
 * @Description: 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
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
        System.out.println(isMatch("abc", "a.c"));
        System.out.println(isMatch("abc", "a*c"));
        System.out.println(isMatch("aa", "a*"));


        System.out.println(isMatch(
                "mississippi",
                "mis*is*ip*."
        ));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }

    /**
     * TODO 使用回溯
     * 思路:从末端向前匹配,匹配成功就'剪掉'
     * 设当前点为s[i],p[j],那么有几种情况:
     * 1,字符匹配,i,j分别后退1
     * 2,不匹配,p[j]=*,分几种情况
     * 2.1,*前面的字符单个匹配,i后退1,j后退2
     * 2.2,*前面的字符出现零次,i不变,j后退2(*前面的字符匹配和不匹配都算)
     * 2.3,*前面的字符出现多次,i后退1,j不变
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch1(String s, String p) {

        return false;
    }

    /**
     * https://leetcode-cn.com/problems/regular-expression-matching/solution/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
     * 使用动态规划:状态转移方程记录s的前i个字符和p的前j个字符匹配情况
     * 然后根据具体的匹配情况,进行状态转移
     *
     * @param s
     * @param p
     * @return
     */
    @Score(time = Score.S.C, memory = Score.S.A)
    public static boolean isMatch(String s, String p) {
        //使用状态df方程记录s中第i个字符和p中第j个字符的匹配情况
        boolean[][] df = new boolean[s.length() + 1][p.length() + 1];
        //初始:sp都为空
        df[0][0] = true;
        //s不为空p为空 false
        //s为空p不为空 例如p='a*'可以匹配s=''
        for (int j = 0; j < p.length(); j++)
            if (p.charAt(j) == '*') df[0][j + 1] = df[0][j - 1];
        //sp都不为空,分情况匹配
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    df[i + 1][j + 1] = df[i][j];
                } else if (p.charAt(j) == '*') {
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        //匹配成功,有两种情况:
                        //1,*=1 i回退,j回退2; ex s:ba p:ba*
                        //2,*=0 i不动,j回退2; ex s:aa p:aaa*
                        //1,*>1 j不动,i回退;  ex s:aa p:a*
                        df[i + 1][j + 1] = df[i][j - 1] || df[i][j + 1] || df[i + 1][j - 1];
                    } else {
                        //*只能是0:j回退2 ex s:aa p:aab*
                        df[i + 1][j + 1] = df[i + 1][j - 1];
                    }
                }
            }
        }
        return df[s.length()][p.length()];
    }


    //https://leetcode-cn.com/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/
    static class S {
        public static boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();
            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (p.charAt(j - 1) == '*') {
                        f[i][j] = f[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j] || f[i - 1][j];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        public static boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }
}
