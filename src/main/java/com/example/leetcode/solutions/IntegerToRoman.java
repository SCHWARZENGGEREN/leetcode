package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2019/9/5 14:40
 * @Description: 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * <p>
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * <p>
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * <p>
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        for (int i = 1;i<=10;i++)
            System.out.print("\""+intToRoman(i)+"\""+",");
        System.out.println(intToRoman(654321));
    }

    /**
     * 阿拉伯数字转罗马数字(1-3999)
     * <p>
     * 1         I
     * 5         V
     * 10        X
     * 50        L
     * 100       C
     * 500       D
     * 1000      M
     * 5000      v
     * 10000     x
     * 50000     l
     * 100000    c
     * 500000    d
     * 1000000   m
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        if (num <= 0 || num > 1000000) return null;
        StringBuilder sb = new StringBuilder();

        char[][] romanDigss = {
                {'I', 'V', 'X'},
                {'X', 'L', 'C'},
                {'C', 'D', 'M'},
                {'M', 'v', 'x'},
                {'x', 'l', 'c'},
                {'c', 'd', 'm'}
        };

        int dig = 0;
        while (num > 0 && num / 10 >= 0) {
            char[] romanDigs = romanDigss[dig];
            int i = num % 10;
            if (i == 9) {
                sb.append(romanDigs[2])
                        .append(romanDigs[0]);
            } else if (i >= 5) {
                int left = i - 5;
                while (left-- > 0) {
                    sb.append(romanDigs[0]);
                }
                sb.append(romanDigs[1]);
            } else if (i == 4) {
                sb.append(romanDigs[1]).append(romanDigs[0]);
            } else if (i > 0) {
                while (i-- > 0) {
                    sb.append(romanDigs[0]);
                }
            }//0 不处理
            num /= 10;
            dig++;
        }

        return sb.reverse().toString();
    }
}
