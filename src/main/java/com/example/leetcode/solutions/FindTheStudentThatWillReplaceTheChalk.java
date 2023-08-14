package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/9/10 14:08
 * @Description: 1894. 找到需要补充粉笔的学生编号
 * 一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n - 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。
 * 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。
 * 请你返回需要 补充 粉笔的学生 编号 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：chalk = [5,1,5], k = 22
 * 输出：0
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
 * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
 * <p>
 * 示例 2：
 * 输入：chalk = [3,4,1,2], k = 25
 * 输出：1
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
 * - 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
 * - 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
 * - 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
 * - 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
 * 编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
 * <p>
 * 提示：
 * chalk.length == n
 * 1 <= n <= 10^5
 * 1 <= chalk[i] <= 10^5
 * 1 <= k <= 10^9
 */
public class FindTheStudentThatWillReplaceTheChalk {

    public static void main(String[] args) {
        int[] chalk = {3, 4, 1, 2};
        int k = 25;
        k = 7;

        System.out.println(chalkReplacer(chalk, k));
    }

    /**
     * 用所有学生需要的粉笔数对总粉笔数取余
     * <p>
     * 为防止越界问题,一旦累计数超过k就不再累计
     *
     * @param chalk
     * @param k
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.SS)
    public static int chalkReplacer(int[] chalk, int k) {
        int length = chalk.length;
        int[] sum = new int[length];

        sum[0] = chalk[0];
        for (int i = 1; i < length; i++) {
            sum[i] = chalk[i] + sum[i - 1];
            if (sum[i] > k) {
                sum[length - 1] = sum[i];
                break;
            }
        }
        int dest = k % sum[length - 1];

        for (int i = 0; i < length; i++) {
            if (dest < sum[i]) return i;
        }
        return -1;
    }
}
