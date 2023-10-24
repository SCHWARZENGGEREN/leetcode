package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Tips;

/**
 * @Auther: Rxh
 * @Date: 2021/3/15 16:32
 * @Description: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs3(45));

    }


    /**
     * f(n) = f(n-1)+f(n-2),刚好是斐波那契数列
     * 直接用递归计算会超时,根据数列规律在一次遍历中也能算出结果
     * 也就是动态数组
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SS)
    public static int climbStairs3(int n) {
        int res = 0, n_1 = 1, n_2 = 1;
        if (n < 2) return 1;
        while (n-- >= 2) {
            res = n_1 + n_2;
            n_1 = n_2;
            n_2 = res;
        }
        return res;
    }

    /**
     * 逆推法:
     * 第n个台阶只能从第n-1或者n-2个上来。
     * 到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法，已经知道了第1个和第2个台阶的走法，一路加上去。
     * 其实就是斐波那契数列
     * TODO 超时
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    public static int climbStairs2(int n) {
        if (n <= 1) {
            //一阶以下,只有一种走法
            return 1;
        } else {//至少还有两阶,继续向下找
            return climbStairs2(n - 1) +
                    climbStairs2(n - 2);
        }
    }


    /**
     * 由于涉及到排列组合,组合数较大,当n=46时结果会超出int类型空间,
     * 因此计算出46以下所有结果并直接返回
     * TODO 结论:警醒自己不要沉迷于算法而忽视实际情况，上了很好的一课
     *
     * @param n
     * @return
     */
    @Tips(tip = "在实际情况中,像这种答案固定的问题直接将答案算好对系统来说是最优解")
    @Score(time = Score.S.FULL, memory = Score.S.B)
    public static int climbStairs1(int n) {
        switch (n) {
            case 1:return  1;
            case 2:return  2;
            case 3:return  3;
            case 4:return  5;
            case 5:return  8;
            case 6:return  13;
            case 7:return  21;
            case 8:return  34;
            case 9:return  55;
            case 10:return  89;
            case 11:return  144;
            case 12:return  233;
            case 13:return  377;
            case 14:return  610;
            case 15:return  987;
            case 16:return  1597;
            case 17:return  2584;
            case 18:return  4181;
            case 19:return  6765;
            case 20:return  10946;
            case 21:return  17711;
            case 22:return  28657;
            case 23:return  46368;
            case 24:return  75025;
            case 25:return  121393;
            case 26:return  196418;
            case 27:return  317811;
            case 28:return  514229;
            case 29:return 832040;
            case 30:return  1346269;
            case 31:return  2178309;
            case 32:return  3524578;
            case 33:return  5702887;
            case 34:return  9227465;
            case 35:return  14930352;
            case 36:return  24157817;
            case 37:return  39088169;
            case 38:return  63245986;
            case 39:return  102334155;
            case 40:return  165580141;
            case 41:return  267914296;
            case 42:return  433494437;
            case 43:return  701408733;
            case 44:return  1134903170;
            case 45:return  1836311903;
        }
        return 0;
    }
}
