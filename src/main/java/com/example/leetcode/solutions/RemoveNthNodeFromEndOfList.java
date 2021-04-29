package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2019/9/23 10:31
 * @Description: 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：给定的 n 保证是有效的。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthNodeFromEndOfList {


    public static void main(String[] args) {
        ListNode node = ListNodeUtils.getLNByNum(new BigInteger("1234"));
        int n = 2;
        System.out.println(node);
//        System.out.println(removeNthFromEnd(node, n));
        System.out.println(removeNthFromEnd1(node, n));
    }

    /**
     * 移除指定位置的节点:
     * 由于listnode结构无法直接定位目标节点位置,需要先遍历一边获取节点深度,再遍历得到目标节点引用
     * <p>
     * 暴力方法
     *
     * @param head
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head, previous = head;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }

        current = head;
        if (size == n) return head.next;
        while (current != null) {
            if (size == n) {
                previous.next = current.next;
                break;
            }
            previous = current;
            current = current.next;
            size--;
        }
        return head;
    }

    /**
     * 巧妙运用双指针
     * 遍历两个初始节点,先遍历n次,只遍历节点一,然后遍历len-n次,同时遍历两个节点,当第一个节点位置到end时,第二个节点的位置刚好在n处
     *
     * @param head
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode tmp = new ListNode(0), first = head, second = tmp;
        tmp.next = head;
        while (first != null) {
            first = first.next;
            if (n <= 0) {
                second = second.next;
            } else {
                n--;
            }
        }
        second.next = second.next.next;
        return tmp.next;
    }
}
