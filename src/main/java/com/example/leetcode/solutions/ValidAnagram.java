package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/5/20 16:26
 * @Description: 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * https://leetcode-cn.com/problems/valid-anagram/
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    @Score(time = Score.S.SSS, memory = Score.S.SS)
    public static boolean isAnagram(String s, String t) {
        int[] count1, count2;
        return s.length() == t.length() &&
                (count1 = countChars(s))[26] == (count2 = countChars(t))[26] &&
                Arrays.hashCode(count1) == Arrays.hashCode(count2);
    }

    /**
     * 优化 记录出现字母个数
     *
     * @param s
     * @return
     */
    public static int[] countChars(String s) {
        int[] charCounts = new int[27];
        for (char c : s.toCharArray()) {
            if (charCounts[c - 'a'] == 0) charCounts[26]++;
            charCounts[c - 'a']++;
        }
        return charCounts;
    }
}
