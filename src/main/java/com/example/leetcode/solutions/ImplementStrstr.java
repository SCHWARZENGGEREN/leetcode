package com.example.leetcode.solutions;

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
        System.out.println(strStr(haystack, needle));

    }

    /**
     * 使用Stack
     * TODO KMP
     * @param haystack
     * @param needle
     * @return
     */
    @Unsettled
    public static int strStr(String haystack, String needle) {
        if (haystack != null && needle != null && haystack.length() >= needle.length()) {
            if (haystack.equals(needle)) return 0;
            return haystack.indexOf(needle);
//            Stack<Character> stack = new Stack<>();
//            int[] pos = new int[1];
//            for (int i = needle.length() - 1; i >= 0; i--) {
//                stack.push(needle.charAt(i));
//            }
//            int i = 0;
//            while (i < haystack.length()) {
//                pos[0] = i;
//                if (checkAppear(haystack, pos, stack)) {
//                    return pos[0] - needle.length();
//                } else {
//                    i = pos[0];
//                }
//            }
        }
        return -1;
    }

    /**
     * 匹配并计算指针位置
     * TODO 如何确定新的起点??????
     * @param haystack
     * @param pos
     * @param needleStack
     * @return
     */
    private static boolean checkAppear(String haystack, int[] pos, Stack<Character> needleStack) {
        if (needleStack.empty()) return true;
        if (haystack.length() - 1 >= pos[0]) {
            Character peek = needleStack.peek();
            if (haystack.charAt(pos[0]) == peek) {
                pos[0] = pos[0] + 1;
                needleStack.pop();
                if (checkAppear(haystack, pos, needleStack)) {
                    return true;
                }
                needleStack.push(peek);
            }
        }
        return false;
    }
}
