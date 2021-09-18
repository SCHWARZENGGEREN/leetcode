package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.SudokuUtils;

/**
 * @Auther: Rxh
 * @Date: 2019/11/9 16:00
 * @Description: 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 */
public class ValidSudoku {

    public static void main(String[] args) {

        char[][] board = {
                {'.', '.', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'1', '.', '.', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '2', '.', '6', '.', '.'},
                {'.', '9', '.', '.', '.', '.', '.', '7', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '8', '.', '.', '.', '.', '.'}
        };
        SudokuUtils.printBoard(board);
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku1(board));
    }

    /**
     * 记录每行每列每个小九宫格是否包含某数字
     *
     * @param board
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.B)
    public static boolean isValidSudoku1(char[][] board) {
        boolean[][] col = new boolean[9][9];//列
        boolean[][] row = new boolean[9][9];//行
        boolean[][] square = new boolean[9][9];//九宫格

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int numIdx = board[i][j] - '1';
                    int squareIdx = i / 3 * 3 + j / 3;
                    if (col[j][numIdx] || row[i][numIdx] || square[squareIdx][numIdx]) {
                        return false;
                    } else {
                        square[squareIdx][numIdx] = row[i][numIdx] = col[j][numIdx] = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 校验行列以及小九宫格内是否有数字重复
     * TODO 优化
     *
     * @param board
     * @return
     */
    @Score(time = Score.S.B, memory = Score.S.A)
    public static boolean isValidSudoku(char[][] board) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                char current = board[y][x];
                if (current == '.') continue;
                if (checkRepeat(board, x, y, current)) {
                    System.out.println("conflict: x:" + x + " ; y:" + y);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 校验某处点是否有冲突
     *
     * @return
     */
    public static boolean checkRepeat(char[][] board, int _x, int _y, char current) {
        int xOff = _x >= 6 ? 6 : _x >= 3 ? 3 : 0;
        int yOff = _y >= 6 ? 6 : _y >= 3 ? 3 : 0;
        int xTmp, yTmp;
        //小九宫校验
        for (int y = 0; y < 3; y++) {
            yTmp = y + yOff;
            if (yTmp == _y) continue;
            for (int x = 0; x < 3; x++) {
                xTmp = x + xOff;
                if (xTmp == _x) continue;
                if (current == board[yTmp][xTmp]) return true;
            }
        }
        //x轴校验
        for (int x = 0; x < 9; x++) {
            if (x == _x) continue;
            if (board[_y][x] == current) return true;
        }
        //y轴校验
        for (int y = 0; y < 9; y++) {
            if (y == _y) continue;
            if (board[y][_x] == current) return true;
        }
        return false;
    }
}
