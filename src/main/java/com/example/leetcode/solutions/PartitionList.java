package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2022/1/21 16:17
 * @Description: 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {

    public static void main(String[] args) {
//        System.out.println(
//                partition(ListNodeUtils.getLNByArray(1, 4, 3, 2, 5, 2), 3)
//        );
        System.out.println(
                partition(ListNodeUtils.getLNByArray(1), 0)
        );
    }

    /**
     * @param head
     * @param x
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.D)
    public static ListNode partition(ListNode head, int x) {
        ListNode current = head, dump = new ListNode(0);
        List<Integer> numsPre = new ArrayList<>();
        List<Integer> numsSur = new ArrayList<>();
        //直接拆分,重组
        while (current != null) {
            if (current.val < x) {
                numsPre.add(current.val);
            } else {
                numsSur.add(current.val);
            }
            current = current.next;
        }

        numsPre.addAll(numsSur);
        current = dump;
        for (int i = 0; i < numsPre.size(); i++) {
            current = current.next = new ListNode(numsPre.get(i));
        }

        return dump.next;
    }
}
