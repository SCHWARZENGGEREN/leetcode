package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/4/1 15:54
 * @Description: 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * <p>
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * <p>
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *  
 * <p>
 * 提示：
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};

        int[][] newIntervals = insert(intervals, newInterval);
        for (int[] interval : newIntervals) {
            System.out.print(Arrays.toString(interval));
            System.out.print(',');
        }
    }

    /**
     * 逐一判断
     *
     * @param intervals
     * @param newInterval
     * @return TODO
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int start = -1, end = intervals.length - 1, len = intervals.length;

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            //判断起点
            if (newInterval[0] <= interval[1]) {
                //左落在左
                if (newInterval[0] > interval[0]) {
                    newInterval[0] = interval[0];
                }
                start = i;
            }
            //判断终点
            if (newInterval[1] < interval[0]) {
                end = i;
                break;
            } else if (newInterval[1] <= interval[1]) {
                //右落在右
                newInterval[1] = interval[1];
                end = i + 1;
            }
        }


        int[][] res = new int[len][2];
        for (int i = 0; i < len; i++) {
            if (i == start) {
                res[i] = newInterval;
                i = end;
            } else if (i < start) {
                res[i] = intervals[i];
            } else {
                res[i] = intervals[i - (end - start)];
            }
        }
        return res;
    }


    /**
     * 遍历数组,分别找出插入数组左右点插入的位置
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    @Unsettled
    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        int len = intervals.length,
                left = newInterval[0],
                right = newInterval[1],
                insertL = -1,//左侧插入点偏左
                insertR = len;//右侧插入点偏右
        for (int i = 0; i < len; i++) {
            if (insertL == -1) {
                //左侧插入点
                if (left <= intervals[i][1]) {
                    insertL = i;
                }
            }
            if (insertR == len) {
                //右侧插入点
                if (right >= intervals[i][0]) {
                    insertR = i;
                }
            }
        }
        int finalLen = len;
        int[][] res = new int[finalLen][2];

        return null;
    }
}
