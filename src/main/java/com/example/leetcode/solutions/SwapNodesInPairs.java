package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2019/11/20 14:11
 * @Description: 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *  
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode node = ListNodeUtils.getLNByNum(new BigInteger("1234"));
        System.out.println(swapPairs(node));
    }

    /**
     * 单层遍历,简单粗暴
     * 但是交换节点比较烧脑
     *
     * @param head
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static ListNode swapPairs(ListNode head) {
        ListNode current = head, folder = new ListNode(0), tmp = folder;
        folder.next = current;
        while (current != null && current.next != null) {
            tmp.next = current.next;//第二个节点放到前面
            current.next = tmp.next.next;//把原来的第二个节点后面的节点挂到第一个节点后面
            tmp.next.next = current;//把原来的第一个节点挂到原来的第二个节点后面
            //将current初始化为下一个节点,tmp初始化为第二个节点
            current = tmp.next.next.next;
            tmp = tmp.next.next;
        }
        return folder.next;
    }
}
