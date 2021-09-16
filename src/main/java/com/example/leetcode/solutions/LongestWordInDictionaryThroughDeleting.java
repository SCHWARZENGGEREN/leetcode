package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2021/9/14 11:04
 * @Description: 524. 通过删除字母匹配到字典里最长单词
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 示例 1：
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * <p>
 * 示例 2：
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 */
public class LongestWordInDictionaryThroughDeleting {

    /**
     * 永遇乐·京口北固亭怀古
     * 南宋-辛弃疾
     * 千古江山,英雄无觅,孙仲谋处.
     * 舞榭歌台风流总被,雨打风吹去
     * 斜阳草树,寻常巷陌,人道寄奴曾住
     * 想当年,金戈铁马,气吞万里如虎
     * 元嘉草草,封狼居胥,赢得仓皇北顾
     * 四十三年,望中犹记,烽火扬州路
     * 可堪回首,佛狸祠下,一片神鸦社鼓
     * 凭谁问,廉颇老矣,尚能饭否?
     * @param args
     */
    public static void main(String[] args) {
        String s = "abpcplea";
        String[] dictionary = {"ale", "aba", "apple", "monkey", "plea"};

        System.out.println(findLongestWord(s, Arrays.asList(dictionary)));
    }

    @Score(time = Score.S.S, memory = Score.S.B)
    public static String findLongestWord(String s, List<String> dictionary) {
        String destWord = "";
        for (String word : dictionary) {
            if (checkContain(s, word)) {
                if (word.length() > destWord.length() || (word.length() == destWord.length() && word.compareTo(destWord) < 0)) {
                    destWord = word;
                }
            }
        }
        return destWord;
    }

    private static boolean checkContain(String s, String word) {
        if (s.length() >= word.length()) {
            int i = 0, j = 0;

            while (i < s.length() && j < word.length()) {
                if (s.length() - i < word.length() - j) break;
                if (s.charAt(i) == word.charAt(j)) {
                    j++;
                }
                i++;
            }
            return j == word.length();
        }
        return false;
    }
}
