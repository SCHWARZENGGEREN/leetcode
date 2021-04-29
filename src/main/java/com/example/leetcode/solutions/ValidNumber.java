package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2021/4/9 15:54
 * @Description: 65. 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 * a>一个 小数 或者 整数
 * b>（可选）一个 'e' 或 'E',后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 * c>（可选）一个符号字符（'+' 或 '-'）
 * <p>
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * <p>
 * 部分有效数字列举如下：
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * <p>
 * 部分无效数字列举如下：
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * <p>
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：s = ".1"
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 */
public class ValidNumber {

    public static void main(String[] args) {

        String[] strings = {
                "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789",
                "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"
        };
        for (String string : strings) {
            System.out.println(string + " : " + isNumber(string));
        }
    }

    /**
     * 将字符分类,然后列出所有排列类型进行判断
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        int signPos = -1, ePos = -1, nPos = -1, pPos = -1;//正负号位置,'e'位置,数字位置,'.'位置
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '+' || ch == '-') {
                //位置,1>,出现在唉最前面,但后面必须有数字 2>,出现在e后面,但后面必须有数字;3>最前面以及e后面
//                if (signPos >= 0 && ePos <= 0) return false;
                if (signPos == 0) {
                    if (ePos != i - 1) return false;
                } else if (signPos < 0) {
                    if (i != 0 && ePos != i - 1) return false;
                } else {
                    if (ePos != i - 1) return false;
                }
                signPos = i;
            } else if (ch == 'e' || ch == 'E') {
                if (ePos >= 0 || nPos <= 0) return false;
                ePos = i;
            } else if (ch == '.') {

            } else if (ch <= '9' && ch >= '0') {
                nPos = i;
            } else {

            }
        }
        return true;
    }
}
