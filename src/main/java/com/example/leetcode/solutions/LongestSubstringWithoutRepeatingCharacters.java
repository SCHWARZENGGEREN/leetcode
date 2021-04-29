package com.example.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/8/23 16:49
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(S2.lengthOfLongestSubstring("pwwkew"));
    }

    public static class S1 {
        /**
         * TODO outoftime
         *
         * @param s
         * @return
         */
        public static int lengthOfLongestSubstring(String s) {
            int longest = 0, len = s.length();
            List<StringBuilder> subStrs = new ArrayList<>();
            //遍历字符串每个字符,并同步拼接所有可能子串,直至子串中出现重复字符,并统计子串长度
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                String ch = c + "";

                //遍历子串列表,如果子串不包含字符,则拼接字符,若包含字符,则移除字符并统计长度
                for (int j = 0; j < subStrs.size(); j++) {
                    StringBuilder sb = subStrs.get(j);
                    if (sb != null) {
                        if (sb.indexOf(ch) < 0) {
                            sb.append(ch);
                        } else {
                            int length = sb.length();
                            longest = length > longest ? length : longest;//随时记录新的子串长度最值
                            System.out.println("subStr: " + sb.toString());
                            subStrs.set(j, null);
                        }
                    }
                }

                //如果剩余遍历步数小于longest,不再添加新的子串
                if ((len - i - 1) >= longest) {
                    subStrs.add(new StringBuilder(ch));
                }
            }

            for (StringBuilder sb : subStrs) {
                if (null != sb) {
                    int length = sb.length();
                    longest = length > longest ? length : longest;//随时记录新的子串长度最值
                }
            }
            return longest;
        }
    }

    public static class S2 {
        /**
         * 滑动窗口,遍历所有字符,并将其添加到列表中,如果发现字符重复,
         * 则清除之前字符出现第一个位置以及之前的所有字符,并记录字符新旧位置之间的长度
         * 遍历字符串所有字符,
         *
         * @param s
         * @return
         */
        public static int lengthOfLongestSubstring(String s) {
            int lst = 0;
            if (s != null && !s.equals("")) {
                List<Cha> chas = new ArrayList<>();
                int len = s.length();

                for (int i = 0; i < len; i++) {
                    char c = s.charAt(i);
                    Cha cha = new Cha(c);
                    int indexOf = chas.indexOf(cha);
                    if (indexOf >= 0) {
                        if (chas.size() > lst) lst = chas.size();
                        //移除字符第一次出现位置以及之前的所有元素
                        if (indexOf == 0) {
                            chas.remove(0);
                        } else {
                            removeFirsts(chas, indexOf + 1);
                        }
                        //移除原来的字符以及它之前的所有字符,并添加新的重复字符
                    }
                    chas.add(cha);
                }
                if (chas.size() > lst) lst = chas.size();
            }

            return lst;
        }
    }

    public static class S3 {
        /**
         * 滑动窗口,遍历所有字符,并将其添加到列表中,如果发现字符重复,
         * 则清除之前字符出现第一个位置以及之前的所有字符,并记录字符新旧位置之间的长度
         * 遍历字符串所有字符,
         *
         * @param s
         * @return
         */
        public static int lengthOfLongestSubstring(String s) {
            int lst = 0;
            if (s != null && !s.equals("")) {
                List<Character> chars = new ArrayList<>();
                int len = s.length();

                for (int i = 0; i < len; i++) {
                    Character cha = s.charAt(i);
                    int indexOf = chars.indexOf(cha);
                    if (indexOf >= 0) {
                        if (chars.size() > lst) lst = chars.size();
                        //移除字符第一次出现位置以及之前的所有元素
                        if (indexOf == 0) {
                            chars.remove(0);
                        } else {
                            removeFirsts(chars, indexOf + 1);
                        }
                    }
                    chars.add(cha);
                }
                if (chars.size() > lst) lst = chars.size();
            }
            return lst;
        }
    }

    /**
     * 移除前几个元素
     *
     * @param list
     * @param count
     */
    private static <T> void removeFirsts(List<T> list, int count) {
        if (list != null && count > 0) {
            while (count > 0 && list.size() > 0) {
                list.remove(0);
                count--;
            }
        }
    }


    public static class Cha {
        private char ch;

        public Cha(char ch) {
            this.ch = ch;
        }

        public char getCh() {
            return ch;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Cha) && ((Cha) obj).getCh() == this.ch;
        }
    }
}
