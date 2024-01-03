package com.example.leetcode.solutions;

/**
 * @author renxinheng
 * @ClassName FindTheLongestBalancedSubstringOfABinaryString
 * @createTime 2023/11/8
 * @desc 2609. 最长平衡子字符串
 * 给你一个仅由 0 和 1 组成的二进制字符串 s 。
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。
 * 返回  s 中最长的平衡子字符串长度。
 * 子字符串是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "01000111"
 * 输出：6
 * 解释：最长的平衡子字符串是 "000111" ，长度为 6 。
 *
 * 示例 2：
 * 输入：s = "00111"
 * 输出：4
 * 解释：最长的平衡子字符串是 "0011" ，长度为  4 。
 *
 * 示例 3：
 * 输入：s = "111"
 * 输出：0
 * 解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
 *
 * 提示：
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 */
public class FindTheLongestBalancedSubstringOfABinaryString {

    public static void main(String[] args) {
        String s = "01000111";
        System.out.println(findTheLongestBalancedSubstring(s));
    }

    /**
     *
     * @param s
     * @return
     */
    public static int findTheLongestBalancedSubstring(String s) {
        char[] sarray = s.toCharArray();
        //扫描出每一个字符最近位置的不同字符位置,便于计算
        int[] nearestNeighbor = new int[sarray.length];
        char c = sarray[0];
        for (int i = 1; i < sarray.length; i++) {
            if (sarray[i] == c){

            }else {

            }
        }
        int maxLen = 0;
        int i = sarray.length - 1;
        while (i >= 0){
            if (i == '1'){

            }
            i --;
        }

        return maxLen;
    }
}
