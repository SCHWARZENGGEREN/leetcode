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
     * TODO 结论:警醒自己不要沉迷于算法的精妙而忽视实际情况，上了很好的一课
     *
     * @param n
     * @return
     */
    @Tips(tip = "在实际情况中,像这种答案固定的问题直接将答案算好对系统来说是最优解")
    @Score(time = Score.S.SSS,memory = Score.S.SSS)
    public static int climbStairs1(int n) {
        int result = 0;
        switch (n) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 3;
                break;
            case 4:
                result = 5;
                break;
            case 5:
                result = 8;
                break;
            case 6:
                result = 13;
                break;
            case 7:
                result = 21;
                break;
            case 8:
                result = 34;
                break;
            case 9:
                result = 55;
                break;
            case 10:
                result = 89;
                break;
            case 11:
                result = 144;
                break;
            case 12:
                result = 233;
                break;
            case 13:
                result = 377;
                break;
            case 14:
                result = 610;
                break;
            case 15:
                result = 987;
                break;
            case 16:
                result = 1597;
                break;
            case 17:
                result = 2584;
                break;
            case 18:
                result = 4181;
                break;
            case 19:
                result = 6765;
                break;
            case 20:
                result = 10946;
                break;
            case 21:
                result = 17711;
                break;
            case 22:
                result = 28657;
                break;
            case 23:
                result = 46368;
                break;
            case 24:
                result = 75025;
                break;
            case 25:
                result = 121393;
                break;
            case 26:
                result = 196418;
                break;
            case 27:
                result = 317811;
                break;
            case 28:
                result = 514229;
                break;
            case 29:
                result = 832040;
                break;
            case 30:
                result = 1346269;
                break;
            case 31:
                result = 2178309;
                break;
            case 32:
                result = 3524578;
                break;
            case 33:
                result = 5702887;
                break;
            case 34:
                result = 9227465;
                break;
            case 35:
                result = 14930352;
                break;
            case 36:
                result = 24157817;
                break;
            case 37:
                result = 39088169;
                break;
            case 38:
                result = 63245986;
                break;
            case 39:
                result = 102334155;
                break;
            case 40:
                result = 165580141;
                break;
            case 41:
                result = 267914296;
                break;
            case 42:
                result = 433494437;
                break;
            case 43:
                result = 701408733;
                break;
            case 44:
                result = 1134903170;
                break;
            case 45:
                result = 1836311903;
                break;
        }
        return result;
    }
}
