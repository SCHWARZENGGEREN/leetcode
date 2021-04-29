package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Tips;

/**
 * @Auther: Rxh
 * @Date: 2021/4/2 10:32
 * @Description: 1006. 笨阶乘
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 * <p>
 * 示例 1：
 * 输入：4
 * 输出：7
 * 解释：7 = 4 * 3 / 2 + 1
 * <p>
 * 示例 2：
 * 输入：10
 * 输出：12
 * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 * <p>
 * 提示：
 * 1 <= N <= 10000
 * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clumsy-factorial
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClumsyFactorial {

    public static void main(String[] args) {
        System.out.println(clumsy(4));
        System.out.println(clumsy(10));
        System.out.println(clumsy(10000));
    }

    /**
     * 循环使用加减乘除运算(/*+-)
     *
     * @param N
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.S)
    public static int clumsy(int N) {
        int res = 0, midRes = N;

        //遇到加减号就叠加,遇到乘除号就累计
        for (int i = N - 1, idx = 0; i >= 1; i--, idx++) {
            if (idx >= 4) idx = 0;
            if (idx == 0) {
                //*
                midRes *= i;
            } else if (idx == 1) {
                ///
                midRes /= i;
            } else if (idx == 2) {
                //+
                res += i + midRes;
                midRes = 0;
            } else if (idx == 3) {
                //-
                midRes = -i;
            }

        }
        return res + midRes;
    }

    /**
     *
     * 推算数学公式:可以得出N>4时,if(N%4==0) f(N) = N+1;if(N%4==1 || N%4==2) f(N) = N+1;if(N%4==3) f(N) = N-1;
     * N<=4时直接算出结果返回;
     * @param N
     * @return
     */
    @Tips(tip = "在很多算法问题中,数学公式都能起到很好的作用,从数学方面考虑问题也很有必要")
    public static int clumsy1(int N) {

        return 0;
    }
}
