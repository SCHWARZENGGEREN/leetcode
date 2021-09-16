package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/9/5 17:17
 * @Description: 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * <p>
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * <p>
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(S2.romanToInt("MCMXLIX"));
        System.out.println(S2.romanToInt("XI"));

        System.exit(0);
    }

    static class S0 {
        static int romanToInt(String s) {
            for (int i = 1; i <= 1000000; i++) {
                if (s.equals(IntegerToRoman.intToRoman(i))) return i;
            }
            return 0;
        }
    }

    static class S1 {
        /**
         * 对应关系
         * 1         I
         * 5         V
         * 10        X
         * 50        L
         * 100       C
         * 500       D
         * 1000      M
         * 5000      v
         * 10000     x
         * 50000     l
         * 100000    c
         * 500000    d
         * 1000000   m
         */
        @Score(time = Score.S.S, memory = Score.S.S)
        static int romanToInt(String s) {
            if (null != s && "" != s) {
                char[] chars = s.toCharArray();
                int length = chars.length;
                int res = 0;

                for (int i = 0; i < length; i++) {
                    char ch = chars[i];
                    if (ch == 'm') {
                        res += 1000000;
                    } else if (ch == 'd') {
                        res += i != 0 && chars[i - 1] == 'c' ? 400000 : 500000;
                    } else if (ch == 'c') {
                        if (i != length - 1 && (chars[i + 1] == 'm' || chars[i + 1] == 'd')) continue;
                        res += i != 0 && chars[i - 1] == 'x' ? 90000 : 100000;
                    } else if (ch == 'l') {
                        res += i != 0 && chars[i - 1] == 'x' ? 40000 : 50000;
                    } else if (ch == 'x') {
                        if (i != length - 1 && (chars[i + 1] == 'c' || chars[i + 1] == 'l')) continue;
                        res += i != 0 && chars[i - 1] == 'M' ? 9000 : 10000;
                    } else if (ch == 'v') {
                        res += i != 0 && chars[i - 1] == 'M' ? 4000 : 5000;
                    } else if (ch == 'M') {
                        if (i != length - 1 && (chars[i + 1] == 'x' || chars[i + 1] == 'v')) continue;
                        res += i != 0 && chars[i - 1] == 'C' ? 900 : 1000;
                    } else if (ch == 'D') {
                        res += i != 0 && chars[i - 1] == 'C' ? 400 : 500;
                    } else if (ch == 'C') {
                        if (i != length - 1 && (chars[i + 1] == 'M' || chars[i + 1] == 'D')) continue;
                        res += i != 0 && chars[i - 1] == 'X' ? 90 : 100;
                    } else if (ch == 'L') {
                        res += i != 0 && chars[i - 1] == 'X' ? 40 : 50;
                    } else if (ch == 'X') {
                        if (i != length - 1 && (chars[i + 1] == 'C' || chars[i + 1] == 'L')) continue;
                        res += i != 0 && chars[i - 1] == 'I' ? 9 : 10;
                    } else if (ch == 'V') {
                        res += i != 0 && chars[i - 1] == 'I' ? 4 : 5;
                    } else if (ch == 'I') {
                        if (i != length - 1 && (chars[i + 1] == 'X' || chars[i + 1] == 'V')) continue;
                        res += 1;
                    }
                }
                return res;
            }
            return 0;
        }
    }

    static class S2 {
        /**
         * 对应关系
         * 1         I
         * 5         V
         * 10        X
         * 50        L
         * 100       C
         * 500       D
         * 1000      M
         * 5000      v
         * 10000     x
         * 50000     l
         * 100000    c
         * 500000    d
         * 1000000   m
         */
        @Score(time = Score.S.SS, memory = Score.S.S)
        static int romanToInt(String s) {
            if (null != s && "" != s) {
                char[] chars = s.toCharArray();
                int length = chars.length;
                int res = 0;

                char before = chars[0];
                for (int i = 0; i < length; i++) {
                    char ch = chars[i];
                    if (ch == 'm') {
                        res += 1000000;
                        if (before == 'c') res -= 200000;
                    } else if (ch == 'd') {
                        res += 500000;
                        if (before == 'c') res -= 200000;
                    } else if (ch == 'c') {
                        res += 100000;
                        if (before == 'x') res -= 20000;
                    } else if (ch == 'l') {
                        res +=50000;
                        if (before == 'x') res -= 20000;
                    } else if (ch == 'x') {
                        res += 10000;
                        if (before == 'M') res -= 2000;
                    } else if (ch == 'v') {
                        res += 5000;
                        if (before == 'M') res -= 2000;
                    } else if (ch == 'M') {
                        res += 1000;
                        if (before == 'C') res -= 200;
                    } else if (ch == 'D') {
                        res += 500;
                        if (before == 'C') res -= 200;
                    } else if (ch == 'C') {
                        res += 100;
                        if (before == 'X') res -= 20;
                    } else if (ch == 'L') {
                        res += 50;
                        if (before == 'X') res -= 20;
                    } else if (ch == 'X') {
                        res += 10;
                        if (before == 'I') res -= 2;
                    } else if (ch == 'V') {
                        res += 5;
                        if (before == 'I') res -= 2;
                    } else if (ch == 'I') {
                        res += 1;
                    }
                    before = ch;
                }
                return res;
            }
            return 0;
        }
    }
}
