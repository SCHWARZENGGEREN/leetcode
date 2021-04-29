package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2020/1/6 16:55
 * @Description: 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams3(strings));
    }

    /**
     * 时间复杂度:O()
     * 以自定义对象保存无重复字符
     *
     * @param strs
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.S)
    public static List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<BoxString, List<String>> stringCountMap = new HashMap<>();
        for (String string : strs) {
            BoxString boxString = new BoxString(string);
            if (!stringCountMap.containsKey(boxString)) {
                stringCountMap.put(boxString, new ArrayList<>());
            }
            stringCountMap.get(boxString).add(string);
        }
        for (Map.Entry<BoxString, List<String>> entry : stringCountMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 将字符排序后的字符作为key
     *
     * @param strs
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SSS)
    public static List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> stringCountMap = new HashMap<>();
        for (String string : strs) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sortString = String.valueOf(chars);

            if (!stringCountMap.containsKey(sortString)) {
                stringCountMap.put(sortString, new ArrayList<>());
            }
            stringCountMap.get(sortString).add(string);
        }
        for (Map.Entry<String, List<String>> entry : stringCountMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    private static final int[] primeNums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    /**
     * 以26个不重复质数标记26个字母,以它们的乘积标识字符串
     * 这种方法很巧妙,不同质数之间的乘积绝不会重复,可以标识每个字母以及出现的次数,但是这种标记方法容易出现数字溢出的情况
     * https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/
     */
    public static List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();//记录每个标识对应res中的角标
        for (String string : strs) {
            Long mark = getPrimeNumMark(string);
            Integer idx = map.get(mark);
            if (idx == null) {
                map.put(mark, (idx = res.size()));
                res.add(new ArrayList<>());
            }
            res.get(idx).add(string);
        }
        return res;
    }

    private static Long getPrimeNumMark(String string) {
        long res = 1;
        for (char ch : string.toCharArray()) {
            res *= primeNums[ch - 'a'];
        }
        return res;
    }


    private static class BoxString {
        String string;
        int[] counts;
        int count = 0;

        public BoxString(String string) {
            if (string != null && string.length() > 0) {
                this.string = string;
                counts = new int[26];
                for (char ch : string.toCharArray()) {
                    int idx = ch - 'a';
                    if (counts[idx] == 0) count++;
                    counts[idx] = counts[idx] + 1;
                }
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof BoxString)) return false;
            BoxString string1 = (BoxString) obj;
            if (this.count == string1.count) {
                if (this.count == 0) return true;
                for (int i = 0; i < 26; i++) {
                    if (this.counts[i] != string1.counts[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return count;
        }
    }
}

