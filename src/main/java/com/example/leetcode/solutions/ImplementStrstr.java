package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;

import java.util.Stack;

/**
 * @Auther: Rxh
 * @Date: 2019/11/21 14:30
 * @Description: 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中
 * 找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * <p>
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 */
public class ImplementStrstr {

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issip";

//        haystack = "abc";
//        needle = "c";
//
//        haystack = "abcd";
//        needle = "cd";
        System.out.println(strStr(haystack, needle));

    }

    /**
     * TODO KMP
     *
     * @param haystack
     * @param needle
     * @return
     */
    @Unsettled
    public static int strStr1(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        char[] s = haystack.toCharArray(), p = needle.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }

    /**
     * @param haystack
     * @param needle
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static int strStr(String haystack, String needle) {
        if ("".equals(needle)) return 0;
        int needleL = needle.length(), haystackL = haystack.length(), minSubLen = 0;
        if (haystackL >= needleL) {
            //每个点遍历,累计每个点子串重合度
            int[] subStrLens = new int[haystackL];
            char firstN = needle.charAt(0);
            for (int i = 0; i < haystackL; i++) {
                if (haystack.charAt(i) == firstN)
                    if (needleL == (subStrLens[i] = 1)) return i;
                //剪枝
                if (minSubLen > (haystackL - 1 - i)) break;
                for (int j = Math.max(0, i - needleL + 1); j < i; j++) {
                    if (subStrLens[j] > 0) {
                        //判断子串
                        if (haystack.charAt(i) == needle.charAt(i - j)) {
                            if (++subStrLens[j] == needleL) return j;
                        } else {
                            subStrLens[j] = 0;
                        }
                    }
                    minSubLen = Math.min(minSubLen, needleL - subStrLens[j]);
                }
            }
        }
        return -1;
    }

}
