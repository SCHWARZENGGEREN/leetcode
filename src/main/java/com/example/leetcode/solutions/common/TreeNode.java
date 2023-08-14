package com.example.leetcode.solutions.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Definition for a binary tree node.
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.val + "";
    }



    /**
     * 按照 root -> left -> right的顺序填装,遇到null就跳过节点
     *
     * @param nums
     * @return
     */
    public static TreeNode buildTreeNode(Integer[] nums) {
        List<TreeNode> lastLevels = new ArrayList<>(), currentLevels = new ArrayList<>();
        TreeNode root = new TreeNode(nums[0]);
        lastLevels.add(root);
        int fIdx = 0;
        boolean left = true;
        for (int i = 1; i < nums.length; i++) {
            if (fIdx == lastLevels.size()) {
                //换行
                fIdx = 0;
                lastLevels.clear();
                lastLevels.addAll(currentLevels);
                currentLevels.clear();
            }
            if (nums[i] != null) {
                TreeNode thisNode = new TreeNode(nums[i]);
                if (left) {
                    lastLevels.get(fIdx).left = thisNode;
                } else {
                    lastLevels.get(fIdx).right = thisNode;
                }
                currentLevels.add(thisNode);
            }

            if (left) {
                left = false;
            } else {
                fIdx++;
                left = true;
            }
        }
        return root;
    }

    /**
     * 以root-left-right遍历tree并打印
     * TODO 优化
     */
    public static void printTreeNodeByRLR(TreeNode treeNode) {
        List<TreeNode> lastLevels = new ArrayList<>(), currentLevels = new ArrayList<>();
        lastLevels.add(treeNode);

        while (!lastLevels.isEmpty()) {
            for (TreeNode fNode : lastLevels) {
                if (Objects.nonNull(fNode)) {
                    System.out.print(fNode.val + ",");
                    currentLevels.add(fNode.left);
                    currentLevels.add(fNode.right);
                } else {
                    System.out.print("null,");
                }
            }
            lastLevels.clear();
            lastLevels.addAll(currentLevels);
            currentLevels.clear();

            System.out.println();
        }
    }
}