package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/9/10 15:19
 * @Description:
 * @imagePath 17_telephone_keypad.png
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class TelephoneKeypad {

    public static void main(String[] args) {
//        System.out.println(S1.letterCombinations("23"));
        System.out.println(S1.letterCombinations("6739"));

        System.exit(0);
    }

    static class S1 {
        /**
         * 排序十分耗时,就不拍了
         * @param digits
         * @return
         */
        @Score(time = Score.S.S,memory = Score.S.S)
        static List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits != null && !digits.equals("")) {
                List<char[]> charArrays = new ArrayList<>();
                char[] digCharArray = digits.toCharArray();
                int digCharArrLen = digCharArray.length;
                for (int idx = 0; idx < digCharArrLen; idx++) {
                    char[] digChars = getCharByDig((byte) (digCharArray[idx] - 48));

                    if (digChars != null) {
                        int digCharsLen = digChars.length, charArraysLen = charArrays.size();
                        if (idx == 0) {
                            for (int j = 0; j < digCharsLen; j++) {
                                char[] chars = new char[digCharArrLen];
                                chars[0] = digChars[j];
                                charArrays.add(chars);
                            }
                            continue;
                        }
                        for (int k = 0; k < digCharsLen * charArraysLen; k++) {
                            char[] chars;
                            if (k < charArraysLen) {
                                chars = charArrays.get(k);
                            } else {
                                chars = getCopyCharArray(charArrays.get(k % charArraysLen));
                                charArrays.add(chars);
                            }
                            chars[idx] = digChars[k / charArraysLen];
                        }
                    }
                }
                for (char[] chars : charArrays) {
                    result.add(new String(chars));
                }
            }
            return result;
        }
    }

    static char[] getCharByDig(byte dig) {
        switch (dig) {
            case 2:
                return new char[]{'a', 'b', 'c'};
            case 3:
                return new char[]{'d', 'e', 'f'};
            case 4:
                return new char[]{'g', 'h', 'i'};
            case 5:
                return new char[]{'j', 'k', 'l'};
            case 6:
                return new char[]{'m', 'n', 'o'};
            case 7:
                return new char[]{'p', 'q', 'r', 's'};
            case 8:
                return new char[]{'t', 'u', 'v'};
            case 9:
                return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }

    static char[] getCopyCharArray(char[] source) {
        int length = source.length;
        char[] target = new char[length];
        for (int i = 0; i < length; i++) {
            char ch = source[i];
            if (ch == '\u0000') {
                break;
            }
            target[i] = ch;
        }
        return target;
    }
}
