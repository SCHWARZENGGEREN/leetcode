package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/5/12 16:28
 * @Description: 1310. 子数组异或查询
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * <p>
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 * <p>
 * 并返回一个包含给定查询 queries 所有结果的数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * 示例 2：
 * <p>
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 10^9
 * 1 <= queries.length <= 3 * 10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XorQueriesOfASubarray {

    public static void main(String[] args) {
//        int[] arr = {1, 15, 8, 4};
//        int[][] queries = {{2, 2}, {0, 0}, {0, 3}, {1, 2}};
//        int[] arr = {1,3,4,8};
//        int[][] queries = {{0,1}, {1,2}, {0, 3}, {3,3}};
        int[] arr = {4, 8, 2, 10};
        int[][] queries = {{2, 3}, {1, 3}, {0, 0}, {0, 3}};
        System.out.println(Arrays.toString(xorQueries(arr, queries)));
        System.out.println(Arrays.toString(xorQueries1(arr, queries)));
    }

    /**
     * 根据异或算法 假如:
     * x = a^b
     * y = b^c = a^b^a^c = x^a^c
     * 即,在原来计算结果上"并入"两个集合中的差集
     * 那么对于queries中的区间来说,
     * 如果跟上个区间有交集,那么将差集的数据异或计算并入上次计算结果即可;
     * 如果没有交集,直接计算
     * 从算法角度来看,算是动态规划思想
     *
     * @param arr
     * @param queries
     * @return
     */
    @Score(time = Score.S.C, memory = Score.S.C)
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int qIdx = 0, resPre = 0;
        int[] queryPre = {30000, -1};//初始化为无效边界
        while (qIdx < queries.length) {
            resPre = (res[qIdx] = getRes(arr, queries[qIdx], queryPre, resPre));
            queryPre = queries[qIdx];
            qIdx++;
        }
        return res;
    }

    /**
     * [10,20] -> [13,17] 缩圈,抛弃
     * [10,20] -> [3,27] 扩圈,添加
     * <p>
     * 扩展时目标范围{current,dest]
     * 缩减时目标范围[current,dest}
     *
     * @param arr
     * @param query
     * @param queryPre
     * @param resPre
     * @return
     */
    private static int getRes(int[] arr,
                              int[] query,
                              int[] queryPre,
                              int resPre) {
        int res = 0, left = query[0], right = query[1],
                leftPre = queryPre[0], rightPre = queryPre[1];
        if (left == right) {
            return arr[left];
        }

        if (right > leftPre && left < rightPre) {
            //区间有交集,补缺损溢
            res = resPre;
            boolean add = false;
            add = leftPre > left;
            while (leftPre != left) {
                if (add) {
                    //增 排除自身
                    res ^= arr[--leftPre];
                } else {
                    //损 从自己开始
                    res ^= arr[leftPre++];
                }
            }

            add = rightPre < right;
            while (rightPre != right) {
                if (add) {
                    //增 排除自身
                    res ^= arr[++rightPre];
                } else {
                    //损 从自己开始
                    res ^= arr[rightPre--];
                }
            }
        } else {
            //没交集,直接计算
            int i = left;
            while (i <= right) {
                res ^= arr[i];
                i++;
            }
        }
        return res;
    }

    /**
     * 计算每个点的累计异或累计值,计算范围是拿出边界两个值直接异或,则重复的部分会被抵消,剩下的就是两个点之间的"差集"
     * 累算每个点的累积异或值到xors
     * xors[i] = arr[i]^arr[i-1]...^arr[0]
     * xors[j] = arr[j]^arr[j-1]...^arr[0]
     * xors[i]^xors[j] = arr[j] ^ arr[j-1]...^arr[i+1]^ arr[i]^arr[i]...^arr[0]^arr[0] = arr^[j,i+1]
     *
     * @param arr
     * @param queries
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.D)
    public static int[] xorQueries1(int[] arr, int[][] queries) {
        int[] xors = new int[arr.length + 1];
        int i = 0;
        while (i < arr.length) {
            xors[i + 1] = xors[i] ^ arr[i];
            i++;
        }

        i = 0;
        int res[] = new int[queries.length];
        while (i < queries.length) {
            res[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];//由于xors中的累积值对比arr错位
            i++;
        }
        return res;
    }
}
