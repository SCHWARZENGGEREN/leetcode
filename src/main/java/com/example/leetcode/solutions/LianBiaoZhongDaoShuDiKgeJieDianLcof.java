package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

/**
 * @Auther: Rxh
 * @Date: 2021/9/2 13:15
 * @Description: 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class LianBiaoZhongDaoShuDiKgeJieDianLcof {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtils.getLNByArray(1, 2, 3, 4, 5);
        System.out.println(getKthFromEnd(listNode, 2));
    }

    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static ListNode getKthFromEnd(ListNode head, int k) {
        int[] kArr = new int[1];
        kArr[0] = k;
        return getNode(head, kArr);
    }

    /**
     * 先正序遍历,到链表尾部后开始k--,减至0返回当前节点
     *
     * @param node
     * @param kArr
     * @return
     */
    private static ListNode getNode(ListNode node, int[] kArr) {
        ListNode res = null;
        if (node.next != null)
            res = getNode(node.next, kArr);

        if (--kArr[0] == 0) res = node;
        return res;
    }
}