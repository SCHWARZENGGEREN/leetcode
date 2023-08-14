package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2021/9/26 10:52
 * @Description: 371. 两整数之和
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 * <p>
 * 示例 1：
 * 输入：a = 1, b = 2
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：a = 2, b = 3
 * 输出：5
 * <p>
 * 提示：
 * -1000 <= a, b <= 1000
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
//        System.out.println(getSum(2, 3));
//        System.out.println(getSum(1000, -1000));

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE+1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));


        System.out.println(getBinaryCode(Integer.MAX_VALUE));
        System.out.println(getBinaryCode(Integer.MIN_VALUE));
        System.out.println(getBinaryCode(-99999));
    }

    /**
     * 使用二进制加减法原理进行计算:
     * 原码:二进制定点表示法，即最高位为符号位，“0”表示正，“1”表示负，其余位表示数值的大小
     * 反码,对于正数,反码与原码相同;对于负数,反码是符号位之外按位取反;
     * 补码:对于正数,补码与原码相同;对于负数,补码是其反码末位加1
     * <p>
     * 加减计算时,直接用两个数字的补码进行码位计算,如果有溢出直接舍弃
     * 这样相当于把加减运算全部按照"加法"规则计算
     *
     * @param a
     * @param b
     * @return
     */
    public static int getSum(int a, int b) {
        StringBuilder aBC = getBinaryCode(a);
        StringBuilder bBC = getBinaryCode(a);
        return 0;
    }


    /**
     * 补码
     *
     * @return
     */
    private static void complementCode(StringBuilder binaryCodes) {
        if (binaryCodes.charAt(0) == '1') {
            //转反码
            for (int i = 1; i < binaryCodes.length(); i++) {
                binaryCodes.setCharAt(i, binaryCodes.charAt(i) == '0' ? '1' : '0');
            }
            //补码
            add(binaryCodes, binaryCodes.length() - 1);
        }
    }

    /**
     * +1
     *
     * @param binaryCodes
     * @param idx
     */
    private static void add(StringBuilder binaryCodes, int idx) {
        boolean carry = true;
        for (int i = idx; i > 0; i--) {
            if (!carry) break;
            if (binaryCodes.charAt(i) == '1') {
                binaryCodes.setCharAt(i, '0');
                carry = true;
            } else {
                binaryCodes.setCharAt(i, '1');
                carry = false;
            }
        }
    }

    private static StringBuilder getBinaryCode(int i) {
        StringBuilder sb = new StringBuilder();
        if (i == Integer.MIN_VALUE) {
            int _i = 32;
            while (_i-- > 0) sb.append('0');
            sb.setCharAt(0, '1');
            return sb;
        }
        sb.append(Integer.toBinaryString(Math.abs(i)));
        while (sb.length() < 32) sb.insert(0, '0');
        if (i < 0) sb.setCharAt(0, '1');
        return sb;
    }
}
