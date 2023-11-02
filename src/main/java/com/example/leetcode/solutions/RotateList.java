package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.solutions.common.ListNode;
import com.example.leetcode.solutions.common.ListNodeUtils;

import java.util.regex.Pattern;

/**
 * @author renxinheng
 * @ClassName RotateList
 * @createTime 2023/10/25
 * @desc 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class RotateList {

    public static void main(String[] args) {
//        String regex = "^\\d{17}[0-9Xx]$";
//        Pattern pattern = Pattern.compile(regex);
        System.out.println(Pattern.matches("^\\d{17}[0-9Xx]$", "410726199110152419"));
        System.out.println(Pattern.matches("^\\d{17}[0-9Xx]$", "41072619911015241X"));
        System.out.println(Pattern.matches("^\\d{17}[0-9Xx]$", "X107261991101524119"));
        System.out.println(Pattern.matches("^\\d{17}[0-9Xx]$", "410726199110152411X"));
//        System.out.println(rotateRight(ListNodeUtils.getLNByArray(1, 2, 3, 4, 5), 5));
//        System.out.println(rotateRight(ListNodeUtils.getLNByArray(1, 2, 3, 4, 5), 36));
    }

    /**
     * 回溯大法
     *
     * @param head
     * @param k
     * @return
     */
    @Score(time = Score.S.FULL, memory = Score.S.C)
    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode temp = new ListNode(0);
        int[] kArray = new int[2];
        kArray[0] = k;
        ergodicNode(head, head, temp, kArray);
        return temp.next;
    }

    /**
     * head -> tail 先找到尾巴,然后把头挂到尾巴上
     * tail -> sub 再逆向找到截取的点,断开并挂接到temp
     *
     * @param head
     * @param tempHead
     * @param k
     */
    public static void ergodicNode(ListNode current, ListNode head, ListNode tempHead, int[] k) {
        k[1]++;//记录深度
        if (current.next != null) {
            //寻找尾巴
            ergodicNode(current.next, head, tempHead, k);
            //找到断点 挂接
            if (k[0] == 0) {
                tempHead.next = current.next;
                current.next = null;
            }
        } else {
            //重新计算k
            k[0] %= k[1];
            if (k[0] != 0) {
                //闭环
                current.next = head;
            } else {
                //原封不动
                tempHead.next = head;
            }
        }
        //记录回溯深度
        k[0]--;
    }
}
