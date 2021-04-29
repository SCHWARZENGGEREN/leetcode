package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.SudokuUtils;

/**
 * @Auther: Rxh
 * @Date: 2019/11/7 17:00
 * @Description: 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        SudokuUtils.printBoard(board);
        solveSudoku(board);
        System.out.println();
        SudokuUtils.printBoard(board);
//        System.out.println(getSudoku(board, 0, 2, '2'));
//        System.out.println(checkSudoku(board, 0, 2, '2'));
    }

    /**
     * 回溯+递归
     * 假设数独只有一个答案,一一测试每个格子并填上数字,然后向后寻找,如果格子内数字不符合数独,直至最后一个格子为止
     *
     * 效率不行哇
     * @param board
     */
    @Score(time = Score.S.B,memory = Score.S.A)
    public static void solveSudoku(char[][] board) {
        recursiveSearchSudokuNum(board, 0, 0);
    }

    private static boolean recursiveSearchSudokuNum(char[][] board, int xIdx, int yIdx) {
        if (xIdx >= 9) {
            if (yIdx >= 8) {
                return true;
            }
            xIdx = 0;
            yIdx++;
        }

        if (board[yIdx][xIdx] != '.') {
            return recursiveSearchSudokuNum(board, xIdx + 1, yIdx);
        } else {
            char ch;
            while ((ch = getSudoku(board, xIdx, yIdx, board[yIdx][xIdx])) != '0') {
                board[yIdx][xIdx] = ch;
                if (recursiveSearchSudokuNum(board, xIdx + 1, yIdx)) return true;
            }
            board[yIdx][xIdx] = '.';
            return false;//未匹配到
        }
    }

    /**
     * 校验数独数字是否冲突
     * 校验规则:1,当前小九宫内不重复;2,横排无重复,3,竖排无重复
     *
     * @param board
     * @param xIdx
     * @param yIdx
     * @return
     */
    private static boolean checkSudoku(char[][] board, int xIdx, int yIdx, char current) {
        int xOff = xIdx >= 6 ? 6 : xIdx >= 3 ? 3 : 0;
        int yOff = yIdx >= 6 ? 6 : yIdx >= 3 ? 3 : 0;
        int xTmp, yTmp;
        //小九宫校验
        for (int y = 0; y < 3; y++) {
            yTmp = y + yOff;
            if (yTmp == yIdx) continue;
            for (int x = 0; x < 3; x++) {
                xTmp = x + xOff;
                if (xTmp == xIdx) continue;
                if (current == board[yTmp][xTmp]) return false;
            }
        }
        //x轴校验
        for (int x = 0; x < 9; x++) {
            if (x == xIdx) continue;
            if (board[yIdx][x] == current) return false;
        }
        //y轴校验
        for (int y = 0; y < 9; y++) {
            if (y == yIdx) continue;
            if (board[y][xIdx] == current) return false;
        }
        return true;
    }

    /**
     * 获取无冲突数独数字
     *
     * @param board
     * @param xIdx  当前位置
     * @param yIdx  当前位置
     * @param min   最小值
     * @return
     */
    private static char getSudoku(char[][] board, int xIdx, int yIdx, char min) {
        if (min == '.') min = '0';
        for (char i = ++min; i <= '9'; i++) {
            if (checkSudoku(board, xIdx, yIdx, i)) return i;
        }
        return '0';
    }

}
