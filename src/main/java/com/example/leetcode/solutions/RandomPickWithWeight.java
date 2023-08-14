package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2021/8/30 10:22
 * @Description: 528. 按权重随机选择
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 * <p>
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * <p>
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * 示例 2：
 * <p>
 * 输入：
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 * <p>
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex 将被调用不超过 10000 次
 */
public class RandomPickWithWeight {

    public static void main(String[] args) {
        int[] w = {1, 3, 5, 2, 20, 3};
        Solution solution = new Solution(w);
//        System.out.println(solution.pickIndex());

        int sum = IntStream.of(w).sum();
        while (sum-- > 0)
            System.out.println(solution.pickIndex());

    }

    /**
     * 记录idx区间,然后二分查找
     *
     * 直接使用二分比递归快
     */
    static class Solution {
        Random r;
        int[] w;
        int[] sums;
        int len;

        public Solution(int[] w) {
            this.w = w;
            sums = new int[(len = w.length) + 1];
            int i = 0;
            while (i < len) {
                sums[i + 1] = sums[i] + w[i];
                i++;
            }
            r = new Random();
        }

        @Score(time = Score.S.A, memory = Score.S.B)
        public int pickIndex() {
            int randomNum = this.r.nextInt(sums[len]) + 1;
            System.out.println("randomNum: " + randomNum);

            int left = 0, right = len, mid = 0;
            while (left < right) {
                mid = (left + right) / 2;
                if (mid == left) break;
                if (randomNum > sums[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return mid;
//            return dichotomySearch(randomNum, 0, len);
        }

        @Score(time = Score.S.D, memory = Score.S.D)
        public int dichotomySearch(int num, int left, int right) {
            if (right <= left) return -1;
            if (right == left + 1) return left;
            int mid = (left + right) / 2;
            if (num > sums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
            return dichotomySearch(num, left, right);
        }
    }
}
