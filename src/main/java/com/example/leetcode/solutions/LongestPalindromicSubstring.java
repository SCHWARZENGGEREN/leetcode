package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2019/8/26 17:04
 * @Description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
 * 回文:aba 或者 aa
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "asecbsddsbcesadadas";
        s = "cbbd";
        s = "ab";
        s = "aaaaa";
        System.out.println(S1.longestPalindrome(s));
    }

    public static class S1 {

        /**
         * 先统计不重复子串,如果出现重复(并且与前一位重复),则开始回溯子串,直至不重复,并记录子串
         *
         * @param s
         * @return
         */
        public static String longestPalindrome(String s) {
            String back = null;
            int start = -1, end = -1, len = 0;//符合条件子串的起止位置
            char[] chars = null;
            if (s != null) {
                len = s.length();
                if (len > 1) {
                    back = s.substring(0, 1);
                    chars = s.toCharArray();
                    for (int i = 0; i < len; i++) {
                        char ch = chars[i];
                        //避免不必要操作
                        int subLen = end - start + 1;
                        if (i + 1 >= subLen / 2 && len - 1 - i >= subLen / 2) {
                            if (i > 0) {
                                int head = -1, tail = -1, symType = 0;//对称类型:1,对称;2,轴对称
                                //三种情况,双重对称(字符都相同,两种对称都符合),对称,轴对称
                                if (ch == chars[i - 1]) {
                                    //对称
                                    head = i - 1;
                                    tail = i;
                                    symType = 1;
                                }
                                if (i < len - 1 && chars[i - 1] == chars[i + 1]) {
                                    //轴对称  如果两种对称都符合  优先轴对称
                                    head = i - 1;
                                    tail = i + 1;
                                    symType = 2;
                                }
                                //判断字符是否一致并延伸头尾
                                if (symType == 1 || (symType == 2 && ch == chars[i - 1])) {
                                    //如果是双重对称,一直延伸两端至字符不重复位置
                                    while (head >= 1 && chars[head - 1] == ch) {
                                        head--;
                                    }
                                    while (tail <= len - 2 && chars[tail + 1] == ch) {
                                        tail++;
                                    }
                                }
                                //两端移动
                                while (head - 1 >= 0 && tail + 1 <= len - 1 && chars[head - 1] == chars[tail + 1]) {
                                    head--;
                                    tail++;
                                }
                                //比较长短
                                if (subLen <= tail - head) {
                                    start = head;
                                    end = tail;
                                }
                            }
                        }
                    }
                } else {
                    return s;
                }
            }
            return start >= 0 && start <= len - 1 && end >= 0 && end <= len - 1 && start < end ? String.valueOf(chars, start, end - start + 1) : back;
        }
    }

    public static class S2 {

        /**
         * @param s
         * @return
         */
        public static String longestPalindrome(String s) {

            return null;
        }
    }
}
