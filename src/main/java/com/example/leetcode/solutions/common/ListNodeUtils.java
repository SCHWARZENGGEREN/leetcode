package com.example.leetcode.solutions.common;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2019/9/23 10:37
 * @Description:
 */
public class ListNodeUtils {

    public static ListNode getLNByString(String numStr) {
        int length = numStr.length();
        int i = 0;
        char ch = '0';
        ListNode dummy = new ListNode(0),current = dummy;
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
}
