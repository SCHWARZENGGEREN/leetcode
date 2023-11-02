package com.example.leetcode.solutions;

/**
 * @author renxinheng
 * @ClassName HIndexII
 * @createTime 2023/10/30
 * @desc 275. H 指数 II
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 *
 * 示例 1：
 * 输入：citations = [0,1,3,5,6]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *      由于研究者有3篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3 。
 *
 * 示例 2：
 * 输入：citations = [1,2,100]
 * 输出：2
 *
 * 提示：
 * n == citations.length
 * 1 <= n <= 10^5
 * 0 <= citations[i] <= 1000
 * citations 按 升序排列
 */
public class HIndexII {

    public static void main(String[] args) {
        int[] citations = {0,1,3,5,6};
        System.out.println(hIndex(citations));
    }

    /**
     *
     *
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        int length = citations.length;
        int[][] numMatrix = new int[length][2];
        int numIdx = 0;
        int res = 0;
        numMatrix[numIdx][0] = citations[0];
        numMatrix[numIdx][1] = 1;
        for (int i = 0; i < length; i++) {
            int left = length - 1 - i;
            if (citations[i] > numMatrix[numIdx][0]){
                numMatrix[++numIdx][0] = citations[i];
                numMatrix[numIdx][1] = length - 1 - i;
                if (numMatrix[numIdx][i] >= citations[i]){
                    res = numMatrix[numIdx][0];
                }
            }
        }

        return 0;
    }
}
