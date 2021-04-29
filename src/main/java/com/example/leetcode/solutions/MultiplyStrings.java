package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2020/1/19 14:37
 * @Description: 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 */
public class MultiplyStrings {

    public static void main(String[] args) {
//        System.out.println((int) '0');// 48
//        System.out.println((int) '1');// 49
//        System.out.println((char) (48 + 1));
//        System.out.println((int) ' ');//32

        System.out.println(multiply("9", "9"));
        System.out.println(multiply("999", "668"));
        System.out.println(multiply("99", "9"));
        System.out.println(multiply("140", "721"));
    }

    /**
     * 优化:不逆序,直接操作(脑阔疼)
     *
     * @param num1
     * @param num2
     * @return
     */
    @Score(time = Score.S.A,memory = Score.S.B)
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.equals("1")) return num2;
        if (num2.equals("1")) return num1;

        //计算结果长度在[len1+len2-1,len1+len2]
        int len1 = num1.length(), len2 = num2.length(), resLen = len1 + len2, subRes, resIdx = resLen - 1, temp;
        int[] resNums = new int[resLen], digs = new int[resLen];//计算结果和进位数

        for (int i = len1 - 1; i >= 0; i--, resIdx--) {
            int _num1 = num1.charAt(i) - 48;
            if (_num1 == 0) continue;
            for (int j = len2 - 1; j >= 0; j--) {
                int _2num = num2.charAt(j) - 48;
                if (_2num == 0) continue;
                resIdx = i + j + 1;//获取结果对应位数  resLen - 1 - (len1 - 1 - i) - (len2 - 1 - j)
                subRes = _num1 * _2num;
                //将单位数的乘算结果拆成进位数和余数,金位数累加到金位数表对应位置,余数累加到余数表对应位置
                resNums[resIdx] += subRes % 10;
                digs[resIdx] += subRes / 10;
                if (resNums[resIdx] >= 10) {
                    resNums[resIdx] = resNums[resIdx] % 10;
                    digs[resIdx]++;
                }
            }
        }

        //处理进位 和最后结果

        char[] resChars = new char[resLen];
        for (int i = resLen - 1; i >= 0; i--) {
            if (digs[i] > 0) {//计算下一位
                temp = resNums[i - 1] + digs[i];
                resNums[i - 1] = temp % 10;
                digs[i - 1] += temp / 10;
            }
            resChars[i] = (char) (resNums[i] + 48);
        }
        int off = 0;
        if (resChars[0] == '0') resLen -= (off = 1);
        return String.valueOf(resChars, off, resLen);
    }

}
