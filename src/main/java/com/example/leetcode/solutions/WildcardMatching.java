package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

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
 *
 */
public class WildcardMatching {

    public static void main(String[] args) {
        /*
        acdcb
        a*c?b

            0  a  c  d  c  b
         0  t
         a     t
         *     t  t  t  t  t
         c        t     t
         ?           t     t
         b
         */
        System.out.println(isMatch("acdcb", "a*c?b"));
                /*
        adceb
        *a*b

           0  a  d  c  e  b
        0  t
        *  t  t  t  t  t  t
        a     t
        *     t  t  t  t  t
        b                 t
         */
        System.out.println(isMatch("adceb", "*a*b"));

    }

    /**
     * https://leetcode-cn.com/problems/wildcard-matching/solution/yi-ge-qi-pan-kan-dong-dong-tai-gui-hua-dpsi-lu-by-/
     * <image>44_wildcard-matching-chess-board1.png</>
     * 假设匹配过程是一个棋子走到终点的过程,以pattern为纵轴,s为横轴,那么棋子走到右下角就算匹配成功,
     * 我们构建一个"棋盘"boolean board[p_len+1][s_len+1];
     * board[0][0]表示出发点,board[i+1][j+1]表示从0-i这段pattern与 从0到j这段s是否匹配
     * 对于pattern中的普通字符或者?,只能匹配一个字符,只能往右下角行进,如果左上角匹配,当前位置也匹配,则记为true;
     * 对于通配符*,它可以匹配0个或n个任意字符,因此它可以认为是从上往下或者从左往右行进,如果左边或者上面匹配,就记为true;
     *
     * @param s
     * @param p
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static boolean isMatch(String s, String p) {
        boolean[][] matches = new boolean[p.length() + 1][s.length() + 1];
        //初发点
        matches[0][0] = true;
        //普通字符:直接匹配
        //"*":匹配该行所有任意字符
        //"?":匹配一个任意字符
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') matches[i + 1][0] = matches[i][0];//行初始位置
            for (int j = 0; j < s.length(); j++) {//由于p的长度可能小于s,每次从头匹配
                if (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j)) {
                    //单个匹配,只走一步,看上一个匹配结果
                    matches[i + 1][j + 1] = matches[i][j];
                } else if (p.charAt(i) == '*') {
                    //如果是pattern第一个字符,直接匹配整行;如果不是,看x轴前一位或y轴前一位
                    matches[i + 1][j + 1] = i == 0 || matches[i][j + 1] || matches[i + 1][j];
                }
            }
        }

        printMatrix(matches);
        return matches[p.length()][s.length()];
    }

    private static void printMatrix(boolean[][] matches) {
        for (boolean[] match : matches) {
            for (boolean b : match) {
                System.out.print(b ? true + " " : false);
                System.out.print(",");
            }
            System.out.println();
        }
    }
}
