package com.example.leetcode.solutions.common;

/**
 * @Auther: Rxh
 * @Date: 2019/11/9 16:04
 * @Description:
 */
public class SudokuUtils {

    public static void printBoard(char[][] board) {
        for (char[] chars : board) {
            for (char ch : chars) {
                System.out.print(ch);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }
}
