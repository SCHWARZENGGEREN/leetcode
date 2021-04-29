package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/9/5 18:36
 * @Description: 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(S1.longestCommonPrefix(new String[]{"aabbaaa", "aabb", "aabb"}));
    }

    static class S1 {
        @Score(time = Score.S.A, memory = Score.S.S)
        static String longestCommonPrefix(String[] strs) {
            if (strs != null && strs.length > 0) {
                StringBuilder sb = new StringBuilder();
                int strsCount = strs.length;
                int length = strs[0].length();

                outer:
                for (int i = 0; i < length; i++) {
                    char ch = strs[0].charAt(i);
                    int loopIdx = strsCount;
                    while (--loopIdx > 0) {//跳过第一个
                        String str = strs[loopIdx];
                        if (str.length() <= i) {
                            break outer;
                        }
                        if (str.charAt(i) != ch) {
                            break outer;
                        }
                    }
                    sb.append(ch);
                }
                return sb.toString();
            }
            return "";
        }
    }
}
