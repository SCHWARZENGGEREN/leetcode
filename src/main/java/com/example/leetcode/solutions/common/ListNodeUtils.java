package com.example.leetcode.solutions.common;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/9/23 10:37
 * @Description:
 */
public class ListNodeUtils {

    public static ListNode getLNByArray(int... nums) {
        ListNode dummy = new ListNode(0), current = dummy;
        for (int num : nums) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    public static ListNode getLNByString(String numStr) {
        int length = numStr.length();
        int i = 0;
        char ch = '0';
        ListNode dummy = new ListNode(0), current = dummy;
        while (i < length) {
            current.next = new ListNode(numStr.charAt(i) - ch);
            current = current.next;
            i++;
        }

        return dummy.next;
    }

    public static ListNode getLNByNum(BigInteger num) {
        return getLNByString(new StringBuilder(num.toString()).reverse().toString());
    }

    public static BigInteger getNumByLN(ListNode l) {
        StringBuilder sb = new StringBuilder();

        while (l != null) {
            sb.append(l.val);
            l = l.next;
        }
        return new BigInteger(sb.reverse().toString());
    }

    /**
     * [1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10, null, null, 11, 12] ->
     * 1, 2, 3, 4, 5, 6, null
     * ------7, 8, 9, 10, null
     * ---------11,12, null
     *
     * @param nums
     * @return
     */
    public static Node getNodeByNums(Integer... nums) {
        Node dump = new Node(null), current = dump;
        if (ArrayUtils.isEmpty(nums)) return dump.next;

        List<Node> tempList = new ArrayList<>();
        Integer num, f_idx;
        for (int i = 0; i < nums.length; i++) {
            if ((num = nums[i]) == null) {
                i++;
                f_idx = 0;
                while (i<nums.length && (num = nums[i]) == null) {
                    i++;
                    f_idx++;
                }
                Node f_node = tempList.get(f_idx);
                current = f_node.child = new Node(num);
                tempList.clear();
            } else {
                current.next = new Node(num);
                current.next.prev = current;
                current = current.next;
            }
            tempList.add(current);
        }

        return dump.next;
    }

    //翻转链表
    public static ListNode reverseNode(ListNode head) {
        ListNode current = head, reverseNode = null;
        while (current != null){
            ListNode tempNode = new ListNode(current.val);
            tempNode.next = reverseNode;
            reverseNode = tempNode;
            current = current.next;
        }
        return reverseNode;
    }
}
