package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/9/16 10:25
 * @Description: 212. 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
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
 * 1 <= words.length <= 3 * 10^4
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
     *
     * @param args
     */
    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain", "hklf", "hf"};

        System.out.println(findWords(board, words));
    }

    /**
     * 使用TrieTree字典树+DFS搜索
     *
     * @param board
     * @param words
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.S)
    public static List<String> findWords(char[][] board, String[] words) {
        //构建字典树
        TrieTree trieTree = new TrieTree();
        for (String word : words) {
            trieTree.insert(word);
        }
        List<String> res = new ArrayList<>();
        //dfs
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(res, board, trieTree.root, new StringBuilder(), i, j);
            }
        }
        dfs(res, board, trieTree.root, new StringBuilder(), 2, 1);
        return res;
    }

    private static void dfs(List<String> res, char[][] board, TrieTree.TrieNode trieNode, StringBuilder sb, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        char ch = board[x][y];
        if (ch == '.') return;//跳过标记

        trieNode = trieNode.children[ch - 'a'];
        if (trieNode != null) {//匹配到trie节点
            int len = sb.length();
            sb.append(ch);
            if (trieNode.isLeaf && !trieNode.used) {
                res.add(sb.toString());
                trieNode.used = true;
            }
            board[x][y] = '.';//标记,防止重复
            //向四个方向搜索
            dfs(res, board, trieNode, sb, x + 1, y);
            dfs(res, board, trieNode, sb, x - 1, y);
            dfs(res, board, trieNode, sb, x, y + 1);
            dfs(res, board, trieNode, sb, x, y - 1);

            board[x][y] = ch;
            sb.deleteCharAt(len);
        }
    }


    static class TrieTree {
        //根节点
        TrieNode root;

        public TrieTree() {
            root = new TrieTree.TrieNode();
        }

        /**
         * 将字符串转换为字符,将字符的每一位视为一个"占位",在tree中占据一个节点
         *
         * @param str
         */
        public void insert(String str) {
            if (str == null || str.length() == 0) return;
            TrieTree.TrieNode temp = root;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                TrieTree.TrieNode node = temp.children[ch - 'a'];//ch仅仅起到定位作用
                if (node == null) {
                    temp.children[ch - 'a'] = (node = new TrieTree.TrieNode());
                }

                if (i == str.length() - 1) {
                    node.isLeaf = true;
                }
                temp = node;
            }
        }

        static class TrieNode {
            boolean isLeaf = false;//是否是某个字符的结尾
            boolean used = false;
            TrieTree.TrieNode[] children;//下级子节点

            TrieNode() {
                this.children = new TrieTree.TrieNode[26];
            }
        }
    }
}
