package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/5/27 16:17
 * @Description: 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，
 * 也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 * 提示：
 * <p>
 * 节点数量不会超过 100000。
 *
 * https://leetcode-cn.com/problems/binode-lcci/submissions/
 */
public class BinodeLcci {

    public static void main(String[] args) {
        Integer[] nums = {
                        4,
                    2,      5,
                1, 3,   null, 6,
                0
        };
        TreeNode.printTreeNodeByRLRL(
                convertBiNode(
                        TreeNode.buildTreeNode(nums)
                )
        );
    }

    /** TODO
     * 直接操作节点
     * @param root
     * @return
     */
    public static TreeNode convertBiNode1(TreeNode root) {

        return root;
    }

    /**
     * 取出所有节点数字,排好序后重新组装到BiNode
     *
     * @param root
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.S)
    public static TreeNode convertBiNode(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        getTreeNums(root, nums);
        nums.sort(Integer::compareTo);

        TreeNode head = new TreeNode(), temp = head;
        int i = 0;
        while (i < nums.size())
            temp = temp.right = new TreeNode(nums.get(i++));

        return head.right;
    }

    public static void getTreeNums(TreeNode root, List<Integer> nums) {
        if (root != null) {
            nums.add(root.val);
            getTreeNums(root.left, nums);
            getTreeNums(root.right, nums);
        }
    }

}
