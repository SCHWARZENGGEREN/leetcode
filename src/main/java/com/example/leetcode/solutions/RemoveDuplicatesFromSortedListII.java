package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

/**
 * @Auther: Rxh
 * @Date: 2022/1/21 14:30
 * @Description: 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        System.out.println(
                deleteDuplicates(ListNodeUtils.getLNByArray(1,1,2,3,3))
        );
    }

    @Score(time = Score.S.SSS,memory = Score.S.C)
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head, prefix = new ListNode(-999);
        prefix.next = current;
        while (current != null) {
            if (current.val == prefix.val) {
                current =prefix.next = current.next;
            } else {
                current = current.next;
                prefix = prefix.next;
            }
        }
        return head;
    }

}
