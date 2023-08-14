package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.TreeNode;

/**
 * @author renxinheng
 * @ClassName MergeTwoBinaryTrees
 * @createTime 2023/8/14
 * @desc 617. 合并二叉树
 * 简单
 * 1.3K
 * 相关企业
 * 给你两棵二叉树： root1 和 root2 。
 * <p>
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * <p>
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 * <p>
 * 示例 1：<image>617_merge_tree.jpg</image>
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 * 示例 2：
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class MergeTwoBinaryTrees {

    public static void main(String[] args) {
        TreeNode tree1 = TreeNode.buildTreeNode(new Integer[]{1, 3, 2, 5});
        TreeNode tree2 = TreeNode.buildTreeNode(new Integer[]{2, 1, 3, null, 4, null, 7});
        tree1 = null;
        TreeNode.printTreeNodeByRLRL(mergeTrees(tree1, tree2));
    }

    /**
     * 直接合并
     *
     * @param root1
     * @param root2
     * @return
     */
    @Score(time = Score.S.SSS)
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//        if (root1 == null){
//            if (root2!= null){
//                root1 = new TreeNode();
//            }else {
//                return null;
//            }
//        }
//        merge(root1, root2);
//        return root1;
        //else use previous
        TreeNode parent1 = new TreeNode();
        parent1.left = root1;
        TreeNode parent2 = new TreeNode();
        parent2.left = root2;

        merge(parent1, parent2);
        return parent1.left;
    }

    /**
     * 直接在root1上合并,分别递归遍历两棵树,如果root1为null,直接把root2节点挂到root1
     * @param root1
     * @param root2
     */
    private static void merge(TreeNode root1, TreeNode root2) {
        if (root2 == null) return;
        root1.val += root2.val;
        if (root1.left != null) {
            merge(root1.left, root2.left);
        } else {
            root1.left = root2.left;
        }
        if (root1.right != null) {
            merge(root1.right, root2.right);
        } else {
            root1.right = root2.right;
        }
    }
}
