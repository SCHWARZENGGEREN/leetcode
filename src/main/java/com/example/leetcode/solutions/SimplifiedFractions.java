package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2022/2/10 10:07
 * @Description: 1447. 最简分数
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 * <p>
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 */
public class SimplifiedFractions {

    public static void main(String[] args) {
        System.out.println(
                simplifiedFractions(15)
        );

        System.out.println(gcd(22,34));
    }

    /**
     * 列举所有范围内最简分数
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.B,memory = Score.S.D)
    public static List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        if (n > 1) {
            //列举法,排除具有相同公约数的组合
            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (checkCommonDivisor(i, j)) continue;
                    res.add(i + "/" + j);
                }
            }
        }
        return res;
    }

    /**
     * 两个正整数是否有相同公约数(排除1)
     * j>i>=1
     * @param i
     * @param j
     * @return
     */
    private static boolean checkCommonDivisor(int i, int j) {
        if (i == 1) return false;
        if (j % i == 0 || (i % 2 == 0 && j % 2 == 0)) return true;
        for (int k = 3; k < i; k++) {
            if (i % k == 0 && j % k == 0) return true;
        }
        return false;
    }

    public static List<String> simplifiedFractions1(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) ans.add(i + "/" + j);
            }
        }
        return ans;
    }

    /**
     * 递归求最小公约数
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) { // 欧几里得算法
        return b == 0 ? a : gcd(b, a % b);
    }
}
