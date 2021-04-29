package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/4/25 10:05
 * @Description: 897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * <p>
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * <p>
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IncreasingOrderSearchTree {

    public static void main(String[] args) {
        Integer[] nums = {
                            5,
                     3,          6,
                2,        4,    null,     8,
            1, null,  null, null,       7,   9
        };
        Integer[] nums1 = {
                    2,
                1,      4,
                null, null, 3
        };
        TreeNode treeNode = TreeNode.buildTreeNode(nums1);
        TreeNode.printTreeNodeByRLRL(increasingBST(treeNode));
    }

    /**
     * 把无需的树按照递增顺序排列
     * 遍历树,如果左侧有节点,则将做子节点与根节点值互换,
     *
     * TODO 优化
     * @param root
     * @return
     */
    @Score(time = Score.S.D,memory = Score.S.D)
    public static TreeNode increasingBST(TreeNode root) {
        //根据顺序排列各节点,并重新组合
        List<TreeNode> nodes = new ArrayList<>();
        splitTreeNode(root, nodes);
        nodes.sort(Comparator.comparingInt(o -> o.val));

        TreeNode temp = nodes.get(0);
        root = temp;
        for (int i = 1; i < nodes.size(); i++) {
            temp.left = null;
            temp.right = nodes.get(i);
            temp = temp.right;
        }
        temp.left = null;
        temp.right = null;

        return root;
    }

    private static void splitTreeNode(TreeNode thisRoot, List<TreeNode> nodes) {
        if (thisRoot == null) return;
        nodes.add(thisRoot);
        splitTreeNode(thisRoot.left, nodes);
        splitTreeNode(thisRoot.right, nodes);
    }


}
