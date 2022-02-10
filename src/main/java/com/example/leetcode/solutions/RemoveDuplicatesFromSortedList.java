package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.util.HashSet;

/**
 * @Auther: Rxh
 * @Date: 2022/1/21 14:30
 * @Description: 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        System.out.println(
                deleteDuplicates(ListNodeUtils.getLNByArray(1, 1, 1, 1))
        );
    }

    /**
     * 删除重复节点,包括第一个
     *
     * @param head
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.B)
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head,
                prePrefix = new ListNode(-999),//前前缀
                prefix = new ListNode(-999), //前缀
                dump = prefix;
        prefix.next = current;
        prePrefix.next = prefix;
        while (current != null) {
            if (current.val == prefix.val) {
                while (current != null && current.val == prefix.val)
                    current = current.next;//删除重复
                //删除prefix
                prefix = prePrefix.next = current;
                if (current != null)
                    current = current.next;
            } else {
                current = current.next;
                prefix = prefix.next;
                prePrefix = prePrefix.next;
            }
        }
        return dump.next;
    }

}
