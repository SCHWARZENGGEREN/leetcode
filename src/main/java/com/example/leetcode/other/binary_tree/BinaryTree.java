package com.example.leetcode.other.binary_tree;

/**
 * @Auther: Rxh
 * @Date: 2019/12/16 13:20
 * @Description: 二叉树:
 * 二叉树(Binary Tree)是n(n≥0)个结点的有限集合BT，
 * 它或者是空集，或者由一个根结点和两棵分别称为左子树和右子树的互不相交的二叉树组成 。
 */
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3), temp;
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(5);

        temp = root.left;
        temp.left = new BinaryTreeNode(0);
        temp.right = new BinaryTreeNode(2);

        temp = root.right;
        temp.left = new BinaryTreeNode(4);
        temp.right = new BinaryTreeNode(7);

//        System.out.println(root.search(4));
        root.midShow();
        root.add(6);
        root.midShow();
    }
}
