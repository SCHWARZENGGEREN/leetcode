package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2022/1/21 13:22
 * @Description: 79. 单词搜索
 * 给定一个 m * n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * https://leetcode-cn.com/problems/word-search/
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";

        System.out.println(exist(board, word));
    }

    /**
     * 回溯
     *
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = m > 0 ? board[0].length : 0, len = word.length(),
                x, y;
        if (m == 0 || n == 0 || word.length() == 0) return false;

        int[] idxCon = new int[1];
        boolean[][] been;
        for (int i = 0, j = 0; i < m; i++) {
            for (; j < n; j++) {
                been = new boolean[m][n];
                x = i;
                y = j;
//                if (search(board, been, idxCon, x, y, word)) {
//                    return true;
//                }
            }
        }

        return idxCon[0] == len;
    }

    /**
     * @param board
     * @param been  是否经过该点
     * @param word
     * @param direct 方向: 0
     * @return
     */
    public static boolean search(char[][] board, boolean[][] been, int[] idxCon,
                                 int m, int n, String word,int direct) {
        if (idxCon[0] == word.length()) return true;//遍历完成
        if (m < 0 || n < 0 || m >= board.length || n >= board[0].length//出界
                || been[m][n]) return false;//重复

        if (word.charAt(idxCon[0]) == board[m][n]) {
            idxCon[0]++;
            return true;
        }
        return false;
    }
}
