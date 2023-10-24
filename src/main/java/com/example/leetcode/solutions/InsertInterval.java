package com.example.leetcode.solutions;

import com.example.leetcode.utils.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/4/1 15:54
 * @Description: 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
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
 * 0 <=intervals[i][0] <=intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <=newInterval[0] <=newInterval[1] <= 10^5
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

        ArrayUtil.printMultiArray(newIntervals);
    }

    /**
     * 三种场景:
     * 1,原区间在新区间左侧,并且无交集
     *  1,没有"合并"起始角标,直接插入原区间左侧
     *  2,有"合并"起始角标,
     * 2,原区间在新区间右侧,并且无交集
     *  继续遍历,直到出现1/3场景的区间
     * 3,原区间与新区间有交集
     *  3.1,如果新区间右侧不大于原区间右侧,直接"吃掉"并替换原区间即可;
     *  3.2,如果新区间右侧大于原区间,标记原区间角标作为"合并"区间的起始
     *
     * @param intervals
     * @param newInterval
     * @return TODO
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int length = intervals.length;
        int insertIdx = -1;//插入区间标记点
        int mergeStartIdx = -1;//合并区间起始点
        int newLeft = newInterval[0];
        int newRight = newInterval[1];
        for (int i = 0; i < length; i++) {
            int[] currInterval = intervals[i];
            if ((newLeft >= currInterval[0] && newLeft <= currInterval[1]) ||
                    newRight >= currInterval[0] && newRight <= currInterval[1]){
                //1 有交集
                if (mergeStartIdx >= 0){
                    //开始合并了
                }else {
                    //没开始
                    if (newLeft < currInterval[0]){
                        currInterval[0] = newLeft;
                    }
                    mergeStartIdx = i;
                }
            }else if (newRight < currInterval[0]){
                //无交集 在左侧
                if (i == 0){
                    insertIdx = 0;
                }
            }else {
                //无交集 在右侧
                if (i == length-1){
                    //插入
                    insertIdx = length - 1;
                }
            }
        }
        if (insertIdx > 0){

        }
        return intervals;
    }
}
