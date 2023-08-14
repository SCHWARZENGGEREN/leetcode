package com.example.leetcode.solutions;

import com.example.leetcode.solutions.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2021/9/28 10:07
 * @Description: 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：[437_pathsum3-1-tree.jpg]
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * <p>
 * 示例 2：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII {

    public static void main(String[] args) {
        Integer[] root = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1
//                          10,
//                        5,  -3,
//                    3,  2,   null, 11,
//                3,-2,  null,1
        };
        int targetSum = 8;

        System.out.println(pathSum(TreeNode.buildTreeNode(root), targetSum));
    }

    /**
     * 前缀map+递归
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int[] res = new int[2];
        res[0] = targetSum;

        dfs(root, prefixSumMap, res);
        return res[1];
    }

    private static void dfs(TreeNode node, Map<Integer, Integer> prefixSumMap, int[] res) {
        if (node == null) return;

        dfs(node.left, prefixSumMap, res);
        dfs(node.right, prefixSumMap, res);
    }
}
