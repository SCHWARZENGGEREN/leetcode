package com.example.leetcode.other.trie_tree;

import com.example.leetcode.utils.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther: Rxh
 * @Date: 2021/11/8 15:28
 * @Description:c 基于二进制位数字的trietree(深度)
 */
public class BinaryTrieTree {

    private TrieNode root;

    public BinaryTrieTree() {
        this.root = new TrieNode();
    }

    public void insert(int num) {
        String binaryString = parseNum2BinaryString(num);

        int idx;
        TrieNode temp = root;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            char ch = binaryString.charAt(i);

            TrieNode node = temp.children[idx = (ch - '0')];//ch仅仅起到定位作用
            if (node == null) {
                temp.children[idx] = (node = new TrieNode());
            }
            if (i == 0){
                node.isLeaf = true;
                node.count++;
            }
            temp = node;
        }
    }

    public boolean searchNum(int num) {
        TrieNode temp = root, node;
        String binaryString = parseNum2BinaryString(num);
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            node = temp.children[binaryString.charAt(i) - '0'];
            if (node == null) break;
            if (i == 0 && node.isLeaf) return true;
            temp = node;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static class TrieNode {
        boolean isLeaf;
        int count;//数字出现次数
        TrieNode[] children;//下级子节点

        TrieNode() {
            this.children = new TrieNode[2];
        }
    }

    private static final String INT_MIN_BINARY = "11111111111111111111111111111111";

    private static String parseNum2BinaryString(int num) {
        if (num == Integer.MIN_VALUE) return INT_MIN_BINARY;
        String binary = Integer.toBinaryString(num < 0 ? -1 * num : num);
        int diffLen = 32 - binary.length();
        if (num < 0 && diffLen > 0) {
            binary = StringUtil.getDuplicateStr(diffLen, "0") + binary;
            binary = binary.replaceFirst("0", "1");
        }

        return binary;
    }

    private static int parseNegativeBinaryString2Num(String binaryString) {
        if (binaryString.equals(INT_MIN_BINARY)) return Integer.MIN_VALUE;
        return binaryString.length() == 32 ?
                -1 * Integer.parseInt(binaryString.substring(1), 2) :
                Integer.parseInt(binaryString, 2);
    }

    public static void main(String[] args) {
        BinaryTrieTree trieTree = new BinaryTrieTree();
        int[] nums = {-33, -17, -3, 19, 5, 2, 3, 47};
        for (int num : nums) trieTree.insert(num);


        System.out.println(Arrays.toString(nums));
        List<String> binaryStrings = IntStream.of(nums)
                .boxed()
                .map(BinaryTrieTree::parseNum2BinaryString)
                .collect(Collectors.toList());
        System.out.println(
                binaryStrings.stream()
                        .collect(Collectors.joining("\n"))
        );

        System.out.println(
                binaryStrings.stream()
                        .map(BinaryTrieTree::parseNegativeBinaryString2Num)
                        .map(trieTree::searchNum)
                        .collect(Collectors.toList())
        );


        System.out.println(trieTree.searchNum(11));
        System.out.println(trieTree.searchNum(-22));
        System.out.println(trieTree.searchNum(33));

    }
}
