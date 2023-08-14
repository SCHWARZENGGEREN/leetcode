package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.TreeNode;

import java.util.LinkedList;

/**
 * @Auther: Rxh
 * @Date: 2021/4/27 10:15
 * @Description: 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * <p>
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * <p>
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *  
 * <p>
 * 提示：
 * 树中节点数目在范围 [1, 2 * 10^4] 内
 * 1 <= Node.val <= 10^5
 * 1 <= low <= high <= 10^5
 * 所有 Node.val 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RangeSumOfBst {

    public static void main(String[] args) {
        Integer[] nums = {
                10,
                5,15,
                3,7,null,18
        };

        TreeNode treeNode = TreeNode.buildTreeNode(nums);
        System.out.println(rangeSumBST(treeNode, 7, 15));
        System.out.println(rangeSumBST2(treeNode, 7, 15));
    }

    /**
     * range节点时,
     * 寻找low的位置:判断 val>root.left.val && val <= root.val,然后向上寻找
     * 寻找hig的位置:判断 val<root.right.val && val #gt or eq(CNMSBWY)root.val,然后向上寻找
     *
     * 这种思路是深度优先?
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        if (root != null) {
            if (root.val >= low && root.val <= high) sum = root.val;
            if (low <= root.val) {
                sum += rangeSumBST(root.left, low, high);
            }
            if (high >= root.val) {
                sum += rangeSumBST(root.right, low, high);
            }
        }
        return sum;
    }

    //节省空间,其实没省到哪去,可读性降低
    public static int rangeSumBST_1(TreeNode root, int low, int high) {
        return root == null ? 0 :
                ((root.val >= low && root.val <= high ? root.val : 0) +
                        (low <= root.val ? rangeSumBST(root.left, low, high) : 0) +
                        (high >= root.val ? rangeSumBST(root.right, low, high) : 0))
                ;
    }

    /**
     * 使用队列存取节点:先将节点放入队列,然后取出:
     * 如果val>high,则将left放入队列;
     * 如果val<low,则将right放入;
     * 如果val>=low&&val<=high,将left和right都放入,并记录val
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static int rangeSumBST2(TreeNode root, int low, int high) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        int sum = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top != null) {
                if (top.val > high) {
                    //向左
                    queue.add(top.left);
                } else if (top.val < low) {
                    //向右
                    queue.add(top.right);
                } else {
                    //root介于[low,high] 记录val,将left和right都加入队列
                    sum += top.val;
                    queue.add(top.left);
                    queue.add(top.right);
                }
            }
        }
        return sum;
    }
}
