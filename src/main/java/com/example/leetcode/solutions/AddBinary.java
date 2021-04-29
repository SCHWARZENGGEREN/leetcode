package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/3/30 16:17
 * @Description: 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "1", b = "11";
        System.out.println(addBinary(a, b));
    }

    /**
     * 跟十进制加法一样,逐步进位即可
     *
     * @param a
     * @param b
     * @return
     */
    @Score(time = Score.S.SS, memory = Score.S.B)
    public static String addBinary(String a, String b) {
        if (a.equals("0")) return b;
        if (b.equals("0")) return a;

        StringBuilder res = new StringBuilder();
        int charAdd;
        boolean carry = false;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            if (i < 0) {
                charAdd = (b.charAt(j) - 48);
            } else if (j < 0) {
                charAdd = (a.charAt(i) - 48);
            } else {
                charAdd = (a.charAt(i) - 48) + (b.charAt(j) - 48);
            }

            if (carry) charAdd++;
            res.append((char) (charAdd % 2 + 48));
            carry = charAdd >= 2;
        }
        if (carry) res.append('1');

        return res.reverse().toString();
    }
}
