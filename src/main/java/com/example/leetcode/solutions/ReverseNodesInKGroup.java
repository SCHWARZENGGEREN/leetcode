package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

/**
 * @Auther: Rxh
 * @Date: 2019/11/20 15:42
 * @Description: 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtils.getLNByString("123456789123");

        System.out.println(reverseKGroup(listNode, 5));
    }


    /**
     * 步骤:1,找到第k个节点;
     * 2,将k节点设为currentHead,然后开始回溯逆序挂节点
     * 3,回溯到头后,拿到currentTail,并将currentHead挂到lastCurrentTail,将currentTail设为lastCurrentTail,然后开始下一轮
     *
     * @param head
     * @param k
     * @return
     */
    @Score(time = Score.S.B, memory = Score.S.C)
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k > 1 && head != null) {
            ListNode dummy = new ListNode(0),
                    currentNode = head,
                    lastTail = dummy,
                    temp = head;
            //根据node深度
            int size = 0;
            while (temp != null) {
                size++;
                temp = temp.next;
            }

            NodeData nodeData = new NodeData();
            while (currentNode != null) {
                if (size < k) {
                    lastTail.next = currentNode;
                    break;
                }

                nodeData.init();
                reverse(currentNode, null, nodeData, k);

                lastTail.next = nodeData.currentHead;
                currentNode = nodeData.nextHead;
                lastTail = nodeData.currentTail;
                if (!nodeData.complete) {
                    //当产生不完全遍历时,没有nextHead,终止遍历
                    break;
                }
                size -= k;
            }
            return dummy.next;
        }
        return head;
    }

    /**
     * 回溯:
     * 步骤:1,找到第k个节点;
     * 2,将k节点设为currentHead,然后开始回溯逆序挂节点
     * 3,回溯到头后,拿到currentTail,并将currentHead挂到lastTail,将currentTail设为lastCurrentTail,然后开始下一轮
     *
     * @param currentNode        当前节点
     * @param lastNode           上一个节点
     * @param data
     * @param k
     */
    private static void reverse(ListNode currentNode,
                                ListNode lastNode,
                                NodeData data,
                                int k) {
        if (k == 1 || currentNode.next == null) {
            if (k != 1) {
                //不完整,不做倒叙处理
                data.currentTail = currentNode;
                data.complete = false;
            } else {
                //完整遍历完k个节点
                data.currentHead = currentNode;
                data.nextHead = currentNode.next;
            }
        } else {
            //继续寻找
            reverse(currentNode.next, currentNode, data, k - 1);
        }

        if (data.complete) {//完整遍历才做倒序处理
            currentNode.next = lastNode;
        }
        if (lastNode == null) {
            if (data.complete) {//完整遍历时将初始点设为currentTail
                data.currentTail = currentNode;
            } else {//不完整遍历将初始点设为currentHead
                data.currentHead = currentNode;
            }
        }
    }


    static class NodeData {
        boolean complete = true;
        ListNode currentHead;
        ListNode currentTail;
        ListNode nextHead;

        void init() {
            complete = true;
        }

        public NodeData() {}
    }
}
