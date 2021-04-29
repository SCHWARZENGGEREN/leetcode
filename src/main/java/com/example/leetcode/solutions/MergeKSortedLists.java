package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: Rxh
 * @Date: 2019/11/19 17:04
 * @Description: 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode ln1 = ListNodeUtils.getLNByNum(new BigInteger("145"));
        ListNode ln2 = ListNodeUtils.getLNByNum(new BigInteger("134"));
        ListNode ln3 = ListNodeUtils.getLNByNum(new BigInteger("26"));
//TODO 为什么重复执行会出现死循环跟内存溢出  导致引用循环
//        System.out.println(mergeKLists(new ListNode[]{ln1, ln2, ln3}));
        System.out.println(mergeKLists1(new ListNode[]{ln1, ln2, ln3}));
    }

    /**
     * 精品答案
     * 使用一个PriorityQuene队列保存Node,并在每次取完值后将next放入quene,会被自动按顺序排列好,然后依次取最小值即可
     *
     * @param lists
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.SSS)
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length > 0) {
            if (lists.length == 1) return lists[0];
            ListNode head = new ListNode(0), current = head;
            PriorityQueue<ListNode> quene = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            });
            for (ListNode node : lists) {
                if (node != null) quene.add(node);
            }
            while (!quene.isEmpty()) {
                ListNode min = quene.poll();
                current.next = min;
                if (min.next != null) {
                    quene.add(min.next);
                }
                current = current.next;
            }
            return head.next;
        }
        return null;
    }

    /**
     * 采用分治法:把复杂问题转变为简单问题
     * 空间换时间,节省了时间,但多层迭代消耗过多内存
     * @param lists
     * @return
     */
    @Score(time = Score.S.SS,memory = Score.S.A)
    public static ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists1(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKLists1(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return lists[right];
        }
        int mid = left + (right - left) / 2;//闭合区间
        return mergeTwoListNode(mergeKLists1(lists, left, mid), mergeKLists1(lists, mid + 1, right));
    }

    private static ListNode mergeTwoListNode(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoListNode(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListNode(l1, l2.next);
            return l2;
        }
    }
}
