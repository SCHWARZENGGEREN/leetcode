package com.example.leetcode.solutions.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/4/27 14:26
 * @Description: 字典树(TrieTree)，又称单词查找树或键树，是一种树形结构，是一种哈希树的变种。
 * 典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
 * 它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
 * Trie的核心思想是空间换时间。利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。
 * <p>
 * 它有3个基本性质：
 * 根节点不包含字符，除根节点外每一个节点都只包含一个字符。
 * 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串。
 * 每个节点的所有子节点包含的字符都不相同。
 * ————————————————
 * 原文链接：https://blog.csdn.net/leasonw/article/details/78009402
 */
public class TrieTree {

    //根节点
    TrieNode root;
    public static final char[] LETTERS = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * 将字符串转换为字符,将字符的每一位视为一个"占位",在tree中占据一个节点
     *
     * @param str
     */
    public void insert(String str) {
        if (str == null || str.length() == 0) return;
        TrieNode temp = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int idx = ch - 'a';
            //全部小写
            if (idx < 0) idx += 32;
            TrieNode node = temp.children[idx];//ch仅仅起到定位作用
            if (node == null) {
                temp.children[idx] = (node = new TrieNode());
            }
            node.prefixCount++;

            if (i == str.length() - 1) {
                node.repeatCount++;
                node.isLeaf = true;
            }
            temp = node;
        }
    }

    public boolean search(String str) {
        return searchPerfix(str, false);
    }

    public boolean startsWith(String str) {
        return searchPerfix(str, true);
    }

    /**
     * @param str
     * @param searchPerfix 搜索前缀or重复
     * @return
     */
    public boolean searchPerfix(String str, boolean searchPerfix) {
        TrieNode destNode = searchNode(str);
        return (destNode != null) && (searchPerfix || destNode.isLeaf);
    }

    public TrieNode searchNode(String str) {
        if (str == null || str.length() == 0) return null;
        TrieNode temp = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            TrieNode node = temp.children[ch - 'a'];
            if (node == null) return null;
            temp = node;
        }
        return temp;
    }

    /**
     * 列出推荐字符:遍历出所有以此作为前缀的词语
     *
     * @param input 输入词语
     * @param limit 限制长度
     * @return
     */
    public List<String> listRecommends(String input, int limit) {
        TrieNode node = searchNode(input);
        List<String> recommends = new ArrayList<>();
        recallListRecommends(node, new StringBuilder(input), recommends, limit - input.length());
        return recommends;
    }

    public List<String> listAll(){
        TrieNode current;
        TrieNode[] children = root.children;
        List<String> allWords = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<LETTERS.length;i++){
            current = children[i];
            if (current != null){
                sb.setLength(0);
                sb.append(LETTERS[i]);
                recallListRecommends(current,sb,allWords,999);

                //换行处理
                if (!allWords.isEmpty()){
                    allWords.set(
                            allWords.size()-1,
                            allWords.get(allWords.size()-1)+"\r\n"
                    );
                }
            }
        }
        return allWords;
    }

    /**
     * 回溯找出所有
     * 先保存:搜索当前节点之前已经获取当前节点对应字母
     * @param currentNode
     * @param builder
     * @param recommends
     * @param depth
     */
    public void recallListRecommends(TrieNode currentNode, StringBuilder builder, List<String> recommends, int depth) {
        if (currentNode == null) return;
        if (currentNode.isLeaf)
            recommends.add(builder.toString());
        if (currentNode.children != null && depth > 0)
            for (int i = 0; i < currentNode.children.length; i++)
                if (currentNode.children[i] != null) {
                    builder.append(LETTERS[i]);
                    recallListRecommends(currentNode.children[i], builder, recommends, depth - 1);
                    builder.setLength(builder.length() - 1);//回溯
                }
    }


    public static class TrieNode {
        int repeatCount = 0;//到此节点为止的字符重复数量
        int prefixCount = 0;//到此节点的字符作为前缀的字符数量
        boolean isLeaf = false;//是否是某个字符的结尾
        TrieNode[] children;//下级子节点

        TrieNode() {
            this.children = new TrieNode[26];
        }
    }

}
