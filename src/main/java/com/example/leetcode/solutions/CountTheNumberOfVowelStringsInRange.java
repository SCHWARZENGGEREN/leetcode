package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName CountTheNumberOfVowelStringsInRange
 * @createTime 2023/11/7
 * @desc 2586. 统计范围内的元音字符串数
 * 给你一个下标从 0 开始的字符串数组 words 和两个整数：left 和 right 。
 * 如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 元音字符串 ，其中元音字母是 'a'、'e'、'i'、'o'、'u' 。
 * 返回 words[i] 是元音字符串的数目，其中 i 在闭区间 [left, right] 内。
 * <p>
 * 示例 1：
 * 输入：words = ["are","amy","u"], left = 0, right = 2
 * 输出：2
 * 解释：
 * - "are" 是一个元音字符串，因为它以 'a' 开头并以 'e' 结尾。
 * - "amy" 不是元音字符串，因为它没有以元音字母结尾。
 * - "u" 是一个元音字符串，因为它以 'u' 开头并以 'u' 结尾。
 * 在上述范围中的元音字符串数目为 2 。
 * <p>
 * 示例 2：
 * 输入：words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
 * 输出：3
 * <p>
 * 解释：
 * - "aeo" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
 * - "mu" 不是元音字符串，因为它没有以元音字母开头。
 * - "ooo" 是一个元音字符串，因为它以 'o' 开头并以 'o' 结尾。
 * - "artro" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
 * 在上述范围中的元音字符串数目为 3 。
 * <p>
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 10
 * words[i] 仅由小写英文字母组成
 * 0 <= left <= right < words.length
 */
public class CountTheNumberOfVowelStringsInRange {

    public static void main(String[] args) {
        String[] words = {"hey", "aeo", "mu", "ooo", "artro"};

        int left = 1, right = 4;
        System.out.println(vowelStrings(words, left, right));
    }

    /**
     * 循环呗
     * 重拳出击
     * @param words
     * @param left
     * @param right
     * @return
     */
    @Score(time = Score.S.FULL,memory = Score.S.A)
    public static int vowelStrings(String[] words, int left, int right) {
        int res = 0, wordLen;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            if (isVowelChar(word.charAt(0)) && (
                    (wordLen = word.length()) == 1 ||
                            isVowelChar(word.charAt(wordLen - 1))
            )) {
                res++;
            }
        }
        return res;
    }

    /*是否为元音字母*/
    public static boolean isVowelChar(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
