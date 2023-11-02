package com.example.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renxinheng
 * @ClassName SmallestValueAfterReplacingWithSumOfPrimeFactors
 * @createTime 2023/11/1
 * @desc 2507. 使用质因数之和替换后可以取到的最小值
 * 给你一个正整数 n 。
 * 请你将 n 的值替换为 n 的 质因数 之和，重复这一过程。
 * 注意，如果 n 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。
 * 返回 n 可以取到的最小值。
 *
 * 示例 1：
 * 输入：n = 15
 * 输出：5
 * 解释：最开始，n = 15 。
 * 15 = 3 * 5 ，所以 n 替换为 3 + 5 = 8 。
 * 8 = 2 * 2 * 2 ，所以 n 替换为 2 + 2 + 2 = 6 。
 * 6 = 2 * 3 ，所以 n 替换为 2 + 3 = 5 。
 * 5 是 n 可以取到的最小值。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：最开始，n = 3 。
 * 3 是 n 可以取到的最小值。
 *
 * 提示：
 * 2 <= n <= 10^5
 */
public class SmallestValueAfterReplacingWithSumOfPrimeFactors {

    public static void main(String[] args) {
        System.out.println(smallestValue(15));
        //列出10000以内所有质数
        System.out.println(listAllPrimeFactors(100000));
    }

    /**
     *
     * @param n
     * @return
     */
    public static int smallestValue(int n) {
        int res = 0;

        return 0;
    }


    private boolean isPrime(int n){
        return false;
    }

    public static List<Integer> listAllPrimeFactors(int n){
        List<Integer> primeFactors = new ArrayList<>();
        int i = 2;
        while (i <= n){
            if (checkIsPrimeFactors(i, primeFactors)){
                primeFactors.add(i);
            }
            i++;
        }
        return primeFactors;
    }

    private static boolean checkIsPrimeFactors(int m, List<Integer> primeFactors){
        for (int i = primeFactors.size() - 1; i >= 0; i--) {
            Integer prime = primeFactors.get(i);
            if (prime >= m) continue;
            if (m%prime == 0){
                return false;
            }
        }
        return true;
    }
}
