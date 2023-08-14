package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2021/3/30 15:50
 * @Description: 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 示例 2：
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        System.out.println(findSubstring(s, words));
    }

    /**
     * 由于子串顺序不定,因此使用每个子串出现次数判断,先确定每个子串出现次数,
     * 然后遍历母串,统计每个子串出现频次,如果出现了未知子串,或者某个子串出现次数>统计次数,就跳过;否则就保存遍历起始点
     *
     * @param s
     * @param words
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.S)
    public static List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), count = words.length, m = words[0].length();
        List<Integer> res = new ArrayList<>();
        if (n >= m * count) {
            Map<String, Integer> wordsMap = new HashMap<>(), statisticMap = new HashMap<>();

            for (String word : words) {
                Integer wordCount = wordsMap.getOrDefault(word, 0);
                wordsMap.put(word, wordCount + 1);
            }

            int j;
            for (int i = 0; i <= (n - count * m); i++) {
                statisticMap.clear();
                for (j = i; j < i + count * m; j += m) {
                    String subStr = s.substring(j, j + m);
                    Integer subStrCount = wordsMap.getOrDefault(subStr, 0);

                    Integer statistiCount = statisticMap.getOrDefault(subStr, 0);
                    statisticMap.put(subStr, ++statistiCount);

                    if (statistiCount > subStrCount) break;
                }
                if (j == i + count * m) res.add(i);//'寿终正寝'
            }
        }
        return res;
    }

    /**
     * TODO 优化:
     * 扫描母串以3n+i为单位,每次前进一个子串的长度,在map中移除旧串并添加新串
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring1(String s, String[] words) {
        int n = s.length(), count = words.length, m = words[0].length();
        List<Integer> res = new ArrayList<>();
        if (n >= m * count) {

        }

        return res;
    }
}
