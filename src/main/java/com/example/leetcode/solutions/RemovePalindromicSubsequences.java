package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2022/1/22 10:34
 * @Description: 1332. 删除回文子序列
 * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 * <p>
 * 示例 1：
 * 输入：s = "ababa"
 * 输出：1
 * 解释：字符串本身就是回文序列，只需要删除一次。
 * <p>
 * 示例 2：
 * 输入：s = "abb"
 * 输出：2
 * 解释："abb" -> "bb" -> "".
 * 先删除回文子序列 "a"，然后再删除 "bb"。
 * <p>
 * 示例 3：
 * 输入：s = "baabb"
 * 输出：2
 * 解释："baabb" -> "b" -> "".
 * 先删除回文子序列 "baab"，然后再删除 "b"。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅包含字母 'a'  和 'b'
 */
public class RemovePalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(removePalindromeSub("baabb"));
    }

    /**
     * 分三种情况:
     * 1,字符串本身回文,一次删除
     * 2,不回文,两次删除(先删除所有a再删除所有b)
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int removePalindromeSub(String s) {
        int len = s.length();
        for (int l = 0, r = len - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return 2;//不满足回文
        }
        return 1;//满足回文
    }
}
