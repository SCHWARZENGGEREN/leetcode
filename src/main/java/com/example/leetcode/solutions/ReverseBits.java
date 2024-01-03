package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/3/29 14:40
 * @Description: 190. 颠倒二进制位
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
 * 并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * <p>
 * 进阶:如果多次调用这个函数，你将如何优化0你的算法？
 * <p>
 * 示例 1：
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * <p>
 * 示例 2：
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 * <p>
 * 提示：输入是一个长度为 32 的二进制字符串
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseBits {

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(43261596));
//        System.out.println(Integer.toBinaryString(964176192));
//        System.out.println(byte2int(new byte[]{1, 1, 1, 1}));
        System.out.println(Integer.MAX_VALUE);
        //00000000000000000000000011111111
        //11111111000000000000000000000000
        System.out.println(reverseBits(255));
        System.out.println(Integer.reverse(255));
        /*
         * 00000010100101000001111010011100
         * 00111001011110000010100101000000
         */
//        System.out.println(reverseBits(43261596));
    }

    /**
     * TODO f**k le**de
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static int reverseBits(int n) {
        return Integer.reverse(n);
//        byte[] bytes = Integer.toBinaryString(n).getBytes();
//        byte[] bytes1 = new byte[32];
//        for (int i = bytes.length - 1, j = 31; i >= 0; j--, i--) {
//            bytes1[j] = bytes[i];
//        }
//
//        return byte2int(bytes1);
    }

    /**
     * 逆向
     *
     * @param bytes
     * @return
     */
    private static int byte2int(byte[] bytes) {
        int res = 0;
        for (int i = bytes.length - 2; i >= 0; i--) {
            if (bytes[i] > 48) {
                res += Math.pow(2, i);
            }
        }
        if (bytes[bytes.length - 1] > 0)
            res *= -1;

        return res;
    }
}
