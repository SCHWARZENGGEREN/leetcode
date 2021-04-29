package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2019/10/31 17:26
 * @Description: 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * <p>
 * 「黑皇后」在棋盘上的位置分布用整数坐标数组 queens 表示，「白国王」的坐标用数组 king 表示。
 * <p>
 * 「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。
 * 请你返回可以直接攻击到「白国王」的所有「黑皇后」的坐标（任意顺序）。
 * <p>
 * example:
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 链接：https://leetcode-cn.com/problems/queens-that-can-attack-the-king
 */
public class QueensThatCanAttackTheKing {

    public static void main(String[] args) {
        int[] king = {1, 1};
        int[][] queens = {{0, 0}, {2, 2}, {1, 0}, {0, 1}, {3, 3}, {4, 0}};
        System.out.println("S1: " + queensAttacktheKing1(queens, king));
        System.out.println("S2: " + queensAttacktheKing2(queens, king));
    }

    /**
     * 这里arr[0]代表y轴,arr[1]代表x轴
     * 如果王后与国王之间有棋子,则无法直接攻击到国王
     * <p>
     * 空间超复杂度低,时间消耗多,比较墨迹
     *
     * @param queens
     * @param king
     * @return
     */
    @Score(time = Score.S.C, memory = Score.S.SSS)
    static List<List<Integer>> queensAttacktheKing1(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> result1 = new ArrayList<>();
        int k_x = king[1], k_y = king[0];
        //记录从k发散的八个方向上遇到的q的个数
        int pos_y = 0, pos_xy = 1, pos_x = 2, pos_x_neg_y = 3, neg_y = 4, neg_xy = 5, neg_x = 6, neg_x_pos_y = 7;
        //记录八个方位王后的分布,key1=dir_type,val1=(key2=result.idx,val2=distance);
        Map<Integer, Map<Integer, Integer>> queenSpread = new HashMap<>();
        for (int[] pos : queens) {
            int q_y = pos[0];
            int q_x = pos[1];
            if (q_x == k_x || q_y == k_y || Math.abs(k_x - q_x) == Math.abs(k_y - q_y)) {
                int dir_type;
                int dir = 0;
                //记录八个方向的点和距离
                if (q_x == k_x) {
                    if (q_y > k_y) {
                        //y正
                        dir_type = pos_y;
                        dir = q_y - k_y;
                    } else {
                        //y负
                        dir_type = neg_y;
                        dir = k_y - q_y;
                    }
                } else if (q_x > k_x) {
                    dir = q_x - k_x;
                    if (q_y > k_y) {
                        //x正y正
                        dir_type = pos_xy;
                    } else if (q_y == k_y) {
                        //x正
                        dir_type = pos_x;
                    } else {
                        //x正y负
                        dir_type = pos_x_neg_y;
                    }
                } else {
                    dir = k_x - q_x;
                    if (q_y > k_y) {
                        //x负y正
                        dir_type = neg_x_pos_y;
                    } else if (q_y == k_y) {
                        //x负
                        dir_type = neg_x;
                    } else {
                        //x负y负
                        dir_type = neg_xy;
                    }
                }

                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(q_y);
                integers.add(q_x);
                result.add(integers);

                Map<Integer, Integer> _queens = queenSpread.get(dir_type);
                if (_queens == null) {
                    _queens = new HashMap<>();
                    queenSpread.put(dir_type, _queens);
                }
                _queens.put(dir, result.size() - 1);
            }
        }
        //排除距离非最小的queen
        List<Integer> validIdxs = new ArrayList<>();
        for (Map<Integer, Integer> qs : queenSpread.values()) {
            if (qs.size() > 0) {
                int minDis = 9;
                for (Integer dis : qs.keySet()) {
                    minDis = Math.min(minDis, dis);
                }
                validIdxs.add(qs.get(minDis));
            }
        }
        for (int i = 0; i < result.size(); i++) {
            if (validIdxs.contains(i)) {
                result1.add(result.get(i));
            }
        }
        return result1;
    }


    /**
     *  先标记棋盘中王后的位置,然后从八个方向遍历并保存每个方向的第一个位置
     * @param queens
     * @param king
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SSS)
    static List<List<Integer>> queensAttacktheKing2(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        int k_x = king[1], k_y = king[0];
        //标记
        boolean[][] mark = new boolean[8][8];
        for (int[] pos : queens) {
            mark[pos[0]][pos[1]] = true;
        }
        //TODO 更好的迭代方式
        //从八个方向遍历
        for (int i = k_x, j = k_y; i < 8; i++) {//pos_x
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; i >= 0; i--) {//neg_x
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; j < 8; j++) {//pos_y
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; j >= 0; j--) {//neg_y
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; i < 8 && j < 8; i++, j++) {//pos_xy
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; i < 8 && j >= 0; i++, j--) {//pos_x_neg_y
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; i >= 0 && j < 8; i--, j++) {//neg_x_pos_y
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        for (int i = k_x, j = k_y; i >= 0 && j >= 0; i--, j--) {//neg_xy
            if (mark[j][i]) {
                result.add(Arrays.asList(j, i));
                break;
            }
        }
        return result;
    }
}
