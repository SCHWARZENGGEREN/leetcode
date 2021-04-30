package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/3/10 16:04
 * @Description: 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：s = " "
 * 输出：0
 *  
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        String s = "Hello World dsadfsga ";
        System.out.println(lengthOfLastWord(s));
    }

    /**
     * 跳过末尾的空格
     * 逆序遍历字符串,到第一个空格stop
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.C)
    public static int lengthOfLastWord(String s) {
        int length, len = 0;
        if (s != null && (length = s.length()) > 0) {
            int i = length - 1;
            boolean notBlank = false;
            while (i >= 0) {
                if (notBlank) {
                    if (s.charAt(i) == ' ') {
                        break;
                    } else {
                        len++;
                    }
                } else {
                    if (s.charAt(i) != ' ') {
                        notBlank = true;
                        len++;
                    }
                }

                i--;
            }
        }
        return len;
    }
}