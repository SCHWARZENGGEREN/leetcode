package com.example.leetcode.solutions;

import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/9/16 10:25
 * @Description: 212. 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * <p>
 * 示例 1：
 * 输入：board = [["o","a","a","n"},{"e","t","a","e"},{"i","h","k","r"},{"i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * <p>
 * 示例 2：
 * 输入：board = [["a","b"},{"c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 */
public class WordSearchII {

    /**
     * 破阵子·为陈同甫赋壮词以寄之
     * 南宋-辛弃疾
     * 醉里挑灯看剑,梦回吹角连营
     * 八百里分麾下炙,五十弦翻塞外声,沙场秋点兵
     * 马作的卢飞快,弓如霹雳弦惊
     * 了却君王天下事,赢得生前身后名,可怜白发生!
     * @param args
     */
    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        System.out.println(findWords(board, words));
    }

    public static List<String> findWords(char[][] board, String[] words) {

        return null;
    }


}
