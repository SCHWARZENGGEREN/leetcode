package com.example.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/10/8 10:35
 * @Description: 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 10^5
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class RepeatedDNASequences {

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 10) {

        }
        return res;
    }
}
