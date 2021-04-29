package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2020/9/1 14:56
 * @Description: 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: intervals = {{1,3},{2,6},{8,10},{15,18}}
 * 输出: {{1,6},{8,10},{15,18}}
 * 解释: 区间 {1,3} 和 {2,6} 重叠, 将它们合并为 {1,6}.
 * <p>
 * 示例 2:
 * 输入: intervals = {{1,4},{4,5}}
 * 输出: {{1,5}}
 * 解释: 区间 {1,4} 和 {4,5} 可被视为重叠区间。
 * <p>
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {6, 6}, {8, 10}, {15, 18}};
        int[][] intervals200 = {{4, 5}, {2, 4}, {4, 6}, {3, 4}, {0, 0}, {1, 1}, {3, 5}, {2, 2}};

        printArr(merge(intervals200));

    }

    /**
     * 对比两个区间的首尾
     * 由于数据乱序,而处理乱序逻辑太复杂,因此先进行排序
     * <p>
     * 虽然能实现,但由于排序效率问题,耗时较多
     *
     * @param intervals
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.A)
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][0];
        }

        sort(intervals);
        int idx = 0, left = intervals[0][0], right = intervals[0][1];
        int[][] res = new int[intervals.length][intervals[0].length];
        for (int i = 1; i < intervals.length; i++) {
            int[] section = intervals[i];
            if (section[0] <= right) {
                //合并区间
                if (section[1] > right)
                    right = section[1];
            } else {
                //隔断区间 保存上个区间
                int[] newSection = new int[2];
                newSection[0] = left;
                newSection[1] = right;

                res[idx++] = newSection;
                left = section[0];
                right = section[1];
            }
        }
        int[] newSection = new int[2];
        newSection[0] = left;
        newSection[1] = right;
        res[idx++] = newSection;
        return Arrays.copyOf(res, idx);
    }

    /**
     * 按left排序
     *
     * @param intervals
     */
    public static void sort(int[][] intervals) {
        int len = intervals.length;
        for (int i = 0; i < len - 1; i++) {
            int iLeft = intervals[i][0];
            for (int j = i + 1; j < len; j++) {
                int jLeft = intervals[j][0];
                if (jLeft < iLeft) {
                    change(intervals, i, j);
                    iLeft = intervals[i][0];
                }
            }
        }
    }

    /**
     * change
     *
     * @param intervals
     * @param i1
     * @param i2
     */
    public static void change(int[][] intervals, int i1, int i2) {
        if (i1 == i2) return;
        int[] temp = intervals[i1];
        intervals[i1] = intervals[i2];
        intervals[i2] = temp;
    }


    private static void printArr(int[][] intervals) {
        printArr1(intervals);
//        System.out.print("{");
//        for (int[] interval : intervals) {
//            System.out.print("[");
//            for (int i : interval) {
//                System.out.print(i);
//                System.out.print(",");
//            }
//            System.out.print("],");
//        }
//        System.out.print("}");
//        System.out.println();
    }

    private static void printArr1(int[][] intervals) {
        System.out.println(
                Stream.of(intervals)
                        .map(Arrays::toString)
                        .collect(Collectors.joining(",", "{", "}"))
        );
    }
}
