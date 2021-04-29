package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/4/21 10:11
 * @Description: 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * <p>
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * <p>
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 * <p>
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeWays {

    public static void main(String[] args) {
        String s = "110111111";
        s = "2101";
        s = "1201234";
        s = "110111111";
        System.out.println(numDecodings(s));
        System.out.println(numDecodings1(s));
    }


    /**
     * 1201234 13
     * 1201234 -5
     * 120123
     * 12012
     * 1201
     *
     *
     *
     * 1201234 21
     *
     */
    //斐波那契数列
    private static final int[] FBNQ = {
            1, 1, 2, 3, 5, 8,
            13, 21, 34, 55, 89,
            144, 233, 377, 610, 987,
            1597, 2584, 4181, 6765,
            10946, 17711, 28657, 46368, 75025,
            121393, 196418, 317811, 514229, 832040,
            1346269, 2178309, 3524578, 5702887, 9227465,
            14930352, 24157817, 39088169, 63245986,
            102334155, 165580141, 267914296, 433494437, 701408733,
            1134903170, 1836311903
    };

    /**
     * 找出输入数字可能来自的解码方式
     * 动态规划思路:
     * 从最后一个数字开始,最后一个数字可能是单个数字,也可能是两位数,如果上一位是0or组成的两位数>26,则排除
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static int numDecodings(String s) {
        if (s.startsWith("0")) return 0;
        if (s.length() == 1) return 1;
        return findDecodes(s, s.length() - 1);
    }

    private static int findDecodes(String s, int idx) {
        if (idx < 0) return 1;
        if (idx == 0) return s.charAt(idx) != '0' ? 1 : 0;
        int direct = s.charAt(idx) == '0' ? 0 : findDecodes(s, idx - 1);
        if (s.charAt(idx - 1) == '1' || (s.charAt(idx - 1) == '2' && s.charAt(idx) <= '6')) {
            direct += findDecodes(s, idx - 2);
        }
        return direct;
    }

    /**
     * 放弃回溯,使用斐波那契剪枝计算
     * 根据斐波那契数列获取每一位理论值,然后剪枝
     *
     * @param s
     * @return
     */
    public static int numDecodings1(String s) {
        int len = s.length();
        if (len == 1) return 1;

        int fullRes = FBNQ[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '0') {
                fullRes -= FBNQ[len - 1 - i];//当前数字是0,排除单个数字的可能性
            } else {
                if (i < len - 1) {
                    if (s.charAt(i + 1) == '0') {
                        if (s.charAt(i) > '2') return 0;//0只能挂在数字后面,如果组成的数字又>=30 ,就会断链
                        //后面是0 要排除前面的可能性,比如 110 只有 1 10 一种组合
                        fullRes -= FBNQ[len - i];
                    }else if (s.charAt(i + 1) > '2' || (s.charAt(i + 1) == '2' && s.charAt(i) > '6')){
                        fullRes -= FBNQ[len - 2 - i];//组成的数字>26,排除拼接下个数字的可能性
                    }
                }
            }
            if (fullRes <= 0) return 0;
        }
        return fullRes;
    }


}
