package com.example.leetcode.solutions;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2021/8/17 10:53
 * @Description: 552. 学生出勤记录 II
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。
 * 答案可能很大，所以返回对 10^9 + 7 取余 的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：8
 * 解释：
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：n = 10101
 * 输出：183236316
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 105
 */
public class StudentAttendanceRecordII {
    public static void main(String[] args) {
//        System.out.println(checkRecord(1));
//        System.out.println(checkRecord(2));
//        System.out.println(checkRecord(3));
//
        System.out.println(checkRecord(10101));
//        System.out.println(checkRecord1(10101));

    }

    /**
     * 使用减法,排除不满足条件的排列方式
     * <p>
     * 所有的情况(T): 不考虑考勤按每一天都有三种出勤,T= 3^n
     * 不满足考勤的情况:
     * 情况1(S1),出现了超过两个的A,不管其他位置如何,至少确定排列中有两个A,选出2个位置放A,S1 = C(n,2) * 3^(n-2)
     * 情况2(S2),出现了至少一个连续3个L,不考虑其他位置,只选出3个位置放3个L,S2 = (n+1-3) * 3^(n-3)
     * 情况3(S3),两个A和连续三个L同时出现,那么要选5个位置放2个A和3个L,由于L是连续的,那么相当于先选出3个连续位置,然后选2个位置, S3 = (n+1-3) * C(n-3,2) * 3^(n-5)
     * <p>
     * 由于在S1和S2中均包含了S3,那么统计时要排除重复的S3, 因此满足出勤的排列数(S) = T - 不满足出勤的排列数
     * 即:S = T - (S1+S2-S3)
     * = 3^n - (C(n,2) * 3^(n-2) + (n+1-3) * 3^(n-3) - (n+1-3) * C(n-3,2) * 3^(n-5))
     *
     * @param n
     * @return
     */
    public static int checkRecord(int n) {
        BigInteger total = new BigInteger("3").pow(n),
                invalidCount = BigInteger.ZERO,
                remind = new BigInteger("1000000007");

        //情况1,出现了超过2个'A'
        //有两个位置确定为A,其他位置都有3种可能,不考虑其余位置,只需要确定两个A出现的位置即可,即: C(n,2)*3^(n-2)
        if (n >= 2)
            invalidCount = invalidCount.add(
                    new BigInteger(countCombination(n, 2) + "")
                            .multiply(pow1(3, n - 2))
            );
        //情况2,出现了连续3个'L'
        //由于是连续的,那么直接在n中选连续3个点,即: (n-3+1) * 3^(n-3)
        if (n >= 3)
            invalidCount = invalidCount.add(new BigInteger((n - 3 + 1) + "").multiply(pow1(3, n - 3)));

        //情况3,同时出现超过2个'A'和连续3个'L'
        //由于情况1和2均包含了情况3,总计之后需要排除多余的情况3,符合条件的情况为: 所有情况-(情况1+情况2-情况3)
        if (n >= 5)//TODO S3> S1,S2 ??
        {
            BigInteger subS3 =
                    new BigInteger((n - 3 + 1) + "")
                            .multiply(new BigInteger(countCombination(n - 3, 2) + ""))
                            .multiply(pow1(3, n - 5));
            //先选出5个位置

            invalidCount = invalidCount.subtract(subS3);
        }






        System.out.println("checkRecord: " + (total.subtract(invalidCount)));
        return total.subtract(invalidCount).mod(remind).intValue();
    }

    public static int checkRecord1(int n) {
        long total = pow(3, n),
                invalidCount = 0,
                remind = pow(10, 9) + 7;

        //情况1,出现了超过2个'A'
        //有两个位置确定为A,其他位置都有3种可能,不考虑其余位置,只需要确定两个A出现的位置即可,即: C(n,2)*3^(n-2)
        if (n >= 2)
            invalidCount += countCombination(n, 2) * (pow(3, n - 2));
        //情况2,出现了连续3个'L'
        //由于是连续的,那么直接在n中选连续3个点,即: (n-3+1) * 3^(n-3)
        if (n >= 3)
            invalidCount += (n - 3 + 1) * (pow(3, n - 3));

        //情况3,同时出现超过2个'A'和连续3个'L'
        //由于情况1和2均包含了情况3,总计之后需要排除多余的情况3,符合条件的情况为: 所有情况-(情况1+情况2-情况3)
        if (n >= 5)
            invalidCount -= (n - 3 + 1) * countCombination(n - 3, 2) * (pow(3, n - 5));


        System.out.println("checkRecord: " + (total - invalidCount));
        return (int) ((total - invalidCount) % remind);
    }

    /**
     * 排列统计:
     * A(m,n) = m!/(m-n)! = m*(m-1)...*(m-n+1)
     *
     * @param m
     * @param n
     * @return
     */
    private static long countArrangement(int m, int n) {
        long res = 1, sub = m - n;
        while (m > sub) res *= m--;

        return res;
    }

    /**
     * 组合统计:
     * C(m,n) = m!/(n!*(m-n)!) = A(m,n)/!(m-n) = m*(m-1)*...(m-n+1)/n!
     *
     * @param m
     * @param n
     * @return
     */
    private static long countCombination(int m, int n) {
        long res = countArrangement(m, n);
        while (n >= 1) res /= n--;

        return res;
    }

    private static long pow(long x, long y) {
        return (long) Math.pow(x, y >= 0 ? y : 0);
    }

    private static BigInteger pow1(int x, int y) {
        return new BigInteger(x + "").pow(y);
    }
}
