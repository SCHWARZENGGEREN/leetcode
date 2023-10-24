package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName SumMultiples
 * @createTime 2023/10/17
 * @desc 2652. 倍数求和
 * 给你一个正整数 n ，请你计算在 [1，n] 范围内能被 3、5、7 整除的所有整数之和。
 * 返回一个整数，用于表示给定范围内所有满足约束条件的数字之和。
 * <p>
 * 示例 1：
 * 输入：n = 7
 * 输出：21
 * 解释：在 [1, 7] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7 。数字之和为 21 。
 * <p>
 * 示例 2：
 * 输入：n = 10
 * 输出：40
 * 解释：在 [1, 10] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7、9、10 。数字之和为 40 。
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：30
 * 解释：在 [1, 9] 范围内能被 3、5、7 整除的所有整数分别是 3、5、6、7、9 。数字之和为 30 。
 * <p>
 * 提示：
 * 1 <= n <= 10^3
 */
public class SumMultiples {

    public static void main(String[] args) {
        System.out.println(sumOfMultiples(15));
        System.out.println(sumOfMultiples1(15));
    }

    /**
     * 直接逆向操作,盘点从1到n里面,3/5/7的倍数
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.B)
    public static int sumOfMultiples(int n) {
        int _3 = 3, _5 = 5, _7 = 7, sum = 0;
        int i = _3;
        while (i <= n) {
            if (i == _3 || i == _5 || i == _7) {
                sum += i;
            }
            if (i == _3) _3 += 3;
            if (i == _5) _5 += 5;
            if (i == _7) _7 += 7;
            i++;
        }
        return sum;
    }

    /**
     * 容斥原理: 在区间内统计所有3,5,7的倍数,并减去所有公倍数
     * 数学公式, 记就完了
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.C)
    public static int sumOfMultiples1(int n) {
        return sum(3, n) + sum(5, n) + sum(7, n) + sum(3 * 5 * 7, n)
                - sum(3 * 5, n) - sum(5 * 7, n) - sum(3 * 7, n);
    }

    /**
     * 等差数列 s(bas) = bas * (n/bas)* (n/bas+ 1)* bas /2
     *
     * @param bas
     * @param n
     * @return
     */
    private static int sum(int bas, int n) {
        int k = n / bas;
        return bas * (k * (k + 1) / 2);
    }
}
