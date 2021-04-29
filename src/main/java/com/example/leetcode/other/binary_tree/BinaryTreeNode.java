package com.example.leetcode.other.binary_tree;

/**
 * @Auther: Rxh
 * @Date: 2019/12/16 13:21
 * @Description: 排序二叉树:是一种特殊的二叉树,具有以下性质
 * 若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 若她的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 具有递归性，排序二叉树的左子树、右子树也是排序二叉树
 */
public class BinaryTreeNode {
    int value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     * DLR（称为先根次序遍历）
     */
    public void frontShow() {
        System.out.println(value);
        if (left != null) {
            left.frontShow();
        }
        if (right != null) {
            right.frontShow();
        }
    }

    /**
     * 中序遍历
     * LDR（称为中根次序遍历）
     */
    public void midShow() {
        if (left != null) {
            left.midShow();
        }
        System.out.println(value);
        if (right != null) {
            right.midShow();
        }
    }

    /**
     * 后序遍历
     * LRD（称为后根次序遍历）
     */
    public void backShow() {
        if (left != null) {
            left.backShow();
        }
        if (right != null) {
            right.backShow();
        }
        System.out.println(value);
    }

    /**
     * 在一个有序二叉树中,按照数字大小查找目标节点
     *
     * @param targetVal
     * @return
     */
    public BinaryTreeNode search(int targetVal) {
        if (value > targetVal) {
            if (left != null) {
                return left.search(targetVal);
            }
        } else if (value < targetVal) {
            if (right != null) {
                return right.search(targetVal);
            }
        } else {
            return this;
        }
        return null;
    }

    /**
     * 添加节点
     * 找到合适位置,并添加到叶子节点
     *
     * @param addVal
     */
    public void add(int addVal) {
        if (value > addVal) {
            if (left != null) {
                left.add(addVal);
            } else {
                left = new BinaryTreeNode(addVal);
            }
        } else if (value < addVal) {
            if (right != null) {
                right.add(addVal);
            } else {
                right = new BinaryTreeNode(addVal);
            }
        }
    }

    /**
     * 先找到节点,如果是叶子节点,直接删除;如果不是,则会涉及到二叉树重排
     *
     * @param delVal
     */
    public void delete(int delVal, BinaryTreeNode parent, BinaryTreeNode root) {
        if (value > delVal) {
            if (left != null) {
                left.delete(delVal, this, root);
            }
        } else if (value < delVal) {
            if (right != null) {
                right.delete(delVal, this, root);
            }
        } else {
            if (parent == null) {
                root = null;
                return;
            }
            boolean thisIsLeft = parent.left == this;
            //找到目标节点
            if (left == null && right == null) {
                //当前节点为叶子节点
                if (thisIsLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (left != null && right != null) {
                //当前节点有左右子节点
                //重新分配当前节点的子节点到tree
                //TODO

            } else if (left != null) {
                //当前节点只有左子节点
                if (thisIsLeft) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
            } else {
                //当前节点只有右子节点
                if (thisIsLeft) {
                    parent.left = right;
                } else {
                    parent.right = right;
                }
            }
        }
    }

}
