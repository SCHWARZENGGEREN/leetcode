package com.example.leetcode.solutions.common;

import com.example.leetcode.utils.StringUtil;

public class Node {
    public Integer val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(Integer num) {
        this.val = num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append(String.valueOf(val)).append(",");
        Node node = next, f_node = null;

        int f_idx = 0, f_idx_temp = 0;
        while (node != null || f_node != null) {
            f_idx_temp++;
            if (node == null) {
                sb.append(", null");
                node = f_node;
                f_node = null;
                sb.append(StringUtil.getDuplicateStr(f_idx,",  "));
            }
            if (node.child == null) {
                if (f_idx == 0) f_idx_temp++;
            } else {
                f_node = node;
                f_idx = f_idx_temp;
                f_idx_temp = 0;
            }
            sb.append(node.val).append(",");
            node = node.next;
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}