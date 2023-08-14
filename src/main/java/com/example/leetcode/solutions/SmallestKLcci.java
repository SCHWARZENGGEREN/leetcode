package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: Rxh
 * @Date: 2021/9/3 10:25
 * @Description: 面试题 17.14. 最小K个数
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
public class SmallestKLcci {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;

        System.out.println(Arrays.toString(smallestK(arr, k)));
    }

    /**
     * 使用自动排序队列
     *
     * @param arr
     * @param k
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.S)
    public static int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        if (arr.length > 0 && k > 0) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(o -> o)));

            for (int i = 0; i < k; i++) {
                priorityQueue.add(arr[i]);
            }
            for (int i = k; i < arr.length; i++) {
                if (arr[i] < priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.offer(arr[i]);
                }
            }
            for (int i = k - 1; i >= 0; i--) {
                res[i] = priorityQueue.poll();
            }
        }
        return res;
    }

    /**
     * 使用快速排序/冒泡排序ect,但只拍前k个数字
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestK1(int[] arr, int k) {
        int[] res = new int[k];
        return res;
    }

}
