package com.example.leetcode.solutions.common;

/**
 * 链表结构模仿
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append(val).append(",");

        ListNode node = next;
        while (node != null) {
            sb.append(node.val).append(",");
            node = node.next;
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}