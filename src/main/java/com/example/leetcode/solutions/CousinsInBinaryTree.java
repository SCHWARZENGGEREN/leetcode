package com.example.leetcode.solutions;

import com.example.leetcode.solutions.common.TreeNode;

/**
 * @Auther: Rxh
 * @Date: 2021/5/17 10:10
 * @Description: 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 */
public class CousinsInBinaryTree {

    public static void main(String[] args) {
        Integer[] root = {
                1,
                2, 3,
                null, 4, null, 5
        };

        root = new Integer[]{
                1,
                2,3,
                null,4
        };
        int x = 2, y = 3;
        System.out.println(
                isCousins(
                        TreeNode.buildTreeNode(root),
                        x, y
                )
        );
    }

    /**
     * 判断x/y是否具有相同深度
     *
     * @param root
     * @param x
     * @param y
     * @return
     */
    public static boolean isCousins(TreeNode root, int x, int y) {
        return getDepth(root, x, 0) == getDepth(root, y, 0);
    }

    public static int getDepth(TreeNode root, int val, int depth) {
        if (root != null) {
            if (val == root.val) {
                System.out.println(String.format("val: %d 的深度为: %d", val, depth));
                return depth;
            } else {
                //向左and向右
                int rDep = getDepth(root.right, val, depth + 1);
                return rDep > 0 ? rDep :
                        getDepth(root.left, val, depth + 1);
            }
        }
        return -1;
    }
}
