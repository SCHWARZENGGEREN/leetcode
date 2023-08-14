package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2021/5/20 13:27
 * @Description: 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * <p>
 * <p>
 * 扩展练习：
 * <p>
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 */
public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        System.out.println(topKFrequent(words, k));
    }

    @Score(time = Score.S.D, memory = Score.S.D)
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Long> wordsFreqMap = Stream.of(words).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));

        return wordsFreqMap
                .keySet()
                .stream()
                .sorted((s1, s2) -> {
                    Long l1 = wordsFreqMap.get(s1);
                    Long l2 = wordsFreqMap.get(s2);
                    return l1.equals(l2) ? s1.compareTo(s2) : l2.compareTo(l1);
                })
                .limit(k)
                .collect(Collectors.toList());
    }

}
