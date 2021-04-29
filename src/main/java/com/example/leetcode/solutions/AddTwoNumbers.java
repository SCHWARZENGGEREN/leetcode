package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2019/8/21 10:28
 * @Description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode ln1 = ListNodeUtils.getLNByNum(new BigInteger("1"));
        ListNode ln2 = ListNodeUtils.getLNByNum(new BigInteger("99999999"));

//        System.out.println(Solution1.addTwoNumbers(Solution1.getLNByNum(new BigInteger("1")), Solution1.getLNByNum(new BigInteger("99999999"))));
        System.out.println(addTwoNumbers(ln1, ln2));
    }

    /**
     * 不直接计算整个结果,而是通过每个节点的数字进行运算
     * @param l1
     * @param l2
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.B)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int baseDig = 10, sum = l1.val + l2.val, remainder = sum % baseDig;
        boolean carry = sum >= 10;//进位
        ListNode result = new ListNode(remainder), resCurrent = result, l1Current = l1.next, l2Current = l2.next;

        while (l1Current != null || l2Current != null || carry) {
            int l1Val = 0, l2Val = 0;
            if (l1Current != null) {
                l1Val = l1Current.val;
                l1Current = l1Current.next;
            }
            if (l2Current != null) {
                l2Val = l2Current.val;
                l2Current = l2Current.next;
            }
            sum = l1Val + l2Val + (carry ? 1 : 0);
            resCurrent.next = new ListNode(sum % baseDig);
            resCurrent = resCurrent.next;
            carry = sum >= 10;
        }
        return result;
    }
}
