package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/10/11 10:23
 * @Description: 273. 整数转换英文表示
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 * 示例 1：
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 *
 * 示例 2：
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 *
 * 示例 3：
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * 示例 4：
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * 提示：
 * 0 <= num <= 2^31 - 1
 */
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        //123,456,7891
        System.out.println(numberToWords(1234567891));
        System.out.println("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");

        System.out.println(numberToWords(Integer.MAX_VALUE));
    }

    private static final String[] _num = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] _ty = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] _teen = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] _dig = {"Billion", "Million", "Thousand", "Hundred", "Ten"};
    private static final int[] _dig_num = {1000000000, 1000000, 1000, 1};

    /**
     * 1000,000,000
     *
     * @param num
     * @return
     */
    @Score(time = Score.S.SS, memory = Score.S.SSS)
    public static String numberToWords(int num) {
        if (num == 0) return _num[0];
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < _dig_num.length && num > 0) {
            if (num >= _dig_num[i]) {
                appendNumLessThan1000(num / _dig_num[i], res);
                if (i < _dig_num.length - 1) res.append(_dig[i]).append(" ");
                num %= _dig_num[i];
            }
            i++;
        }

        if (res.charAt(res.length() - 1) == ' ') res.setLength(res.length() - 1);
        return res.toString();
    }

    private static void appendNumLessThan1000(int num, StringBuilder res) {
        if (num >= 100) {
            res.append(_num[num / 100])
                    .append(" ")
                    .append(_dig[3])
                    .append(" ");
            num %= 100;
        }
        if (num >= 20) {
            res.append(_ty[num / 10 - 1])
                    .append(" ");
            num %= 10;
        } else if (num >= 10) {
            res.append(_teen[num - 10])
                    .append(" ");
            num = 0;
        }
        if (num > 0) {
            res.append(_num[num])
                    .append(" ");
        }
    }
}
