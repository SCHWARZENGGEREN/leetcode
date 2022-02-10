package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2021/9/22 10:16
 * @Description: 725. 分隔链表
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * 返回一个由上述 k 部分组成的数组。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * 解释：
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 1000]
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 */
public class SplitLinkedListInParts {

    public static void main(String[] args) {
        int[] nums = new int[1005];
        int i = 0;
        while (i < 1005) nums[i++] = i;
//        System.out.println(
//                Arrays.toString(splitListToParts(
//                        ListNodeUtils.getLNByArray(nums),
//                        50
//                )));
//        System.out.println(
//                Arrays.toString(splitListToParts(
//                        ListNodeUtils.getLNByArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
//                        3
//                )));
//        System.out.println(
//                Arrays.toString(splitListToParts(
//                        ListNodeUtils.getLNByArray(1, 2, 3, 4, 5, 6, 7),
//                        3
//                )));
        System.out.println(
                Arrays.toString(splitListToParts(
                        null,
                        3
                )));
    }

    /**
     * 使用递归切割node
     * 将node尽可能平均的分成k份,多余的划分到第一段
     *
     * @param head
     * @param k
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static ListNode[] splitListToParts(ListNode head, int k) {
        List<ListNode> res = new ArrayList<>();
        int[] data = new int[5];
        data[1] = k;
        splitListNode(head, data, res, new ArrayList<>());
        ListNode[] nodeArray = new ListNode[Math.max(res.size(), k)];
        for (int i = res.size() - 1, j = 0; i >= 0; i--) {
            nodeArray[j++] = res.get(i);
        }
        return nodeArray;
    }

    /**
     * @param node 当前节点
     * @param data data[0] = currentIdx,当前节点的idx+1;
     *             data[1] = k;
     */
    public static void splitListNode(ListNode node, int[] data, List<ListNode> nodes, List<Integer> indexes) {
        data[0]++;
        if (node == null || node.next == null) {
            splitIndexes(data[0], data[1], indexes);
        } else {
            splitListNode(node.next, data, nodes, indexes);
        }
        if (data[4] == 1) {
            if (node != null) node.next = null;
            data[4] = 0;
        }
        //根据indxes切割
        if (indexes.get(indexes.size() - 1) == data[0]) {
            data[4] = 1;
            nodes.add(node);
            indexes.remove(indexes.size() - 1);
        }

        data[0]--;
    }

    /**
     * 将total个节点按照题意划分为k部分,ep:
     * 3 - 5  => 1, 1,1
     * 7 - 3  => 3, 2,2
     * 10 - 3 => 4, 3,3
     * 13 - 5 => 3,3,3,2,2
     *
     * @param total
     * @param k
     * @param indexes
     * @return
     */
    private static void splitIndexes(int total, int k, List<Integer> indexes) {
        if (total <= 0) return;
        int sub = total > k ? total / k : 1, res = total > k ? total % k : 0, idx, off = 0;
        for (idx = sub; idx <= total; idx += sub) {
            indexes.add(idx + 1);
        }
        while (indexes.size() > 0 && indexes.get(indexes.size() - 1) > total - res)
            indexes.remove(indexes.size() - 1);

        idx = 0;
        //先均分,然后将余数依次从前往后分配
        while (idx < indexes.size()) {
            if (res > 0) {
                res--;
                off++;
            }

            indexes.set(idx, indexes.get(idx) + off);
            idx++;
        }
        indexes.add(0, 1);

        return;
    }
}
