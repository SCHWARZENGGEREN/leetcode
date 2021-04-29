package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.math.BigInteger;

/**
 * @Auther: Rxh
 * @Date: 2019/11/18 15:41
 * @Description: 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtils.getLNByNum(new BigInteger("456"));
        ListNode l2 = ListNodeUtils.getLNByNum(new BigInteger("34"));

        System.out.println(mergeTwoLists(l1, l2));
    }

    /**
     * 直接遍历并比较
     * @param l1
     * @param l2
     * @return
     */
    @Score(time = Score.S.S,memory = Score.S.S)
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode merge = new ListNode(0), current = merge;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                current.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        return merge.next;
    }
}
