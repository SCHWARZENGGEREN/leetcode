package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2021/9/13 11:07
 * @Description: 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * <p>
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * <p>
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * 所有点都 互不相同
 */
public class NumberOfBoomerangs {

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}, {4, 4}};
        System.out.println(numberOfBoomerangs(points));
    }

    /**
     * 题意重释: 给定一个坐标点集合,找出它们中满足条件的三个点的排列组合数量,要求这三个点(p1,p2,p3)满足:p1到p2的距离 等于 p1到p3的距离
     * 按照题意,要找出每个点距离相同的两个点的组合,直接暴力搜索的话工作量很恐怖
     * 对于每个点,直接使用"距离"-"个数"代表距离当前点为x的点的数量,如:"距离" = 1的点有n个,那么要找出它们之中2个组合的数量,即A(n,2) = n*(n-1)
     *
     * @param points
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.B)
    public static int numberOfBoomerangs(int[][] points) {
        int num = 0;
        if (points.length > 2) {
            Map<Integer, Integer> distanceMap = new HashMap<>();//记录 距离-数量
//            Map<Integer, List<String>> printMap = new HashMap<>();//记录 距离-数量
            for (int i = 0; i < points.length; i++) {
//                printMap.clear();
                distanceMap.clear();
                //点之间互不重复,直接计算距离
                for (int j = 0; j < points.length; j++) {
                    if (j == i) continue;
                    int distance = (int) (Math.pow(points[j][0] - points[i][0], 2) + Math.pow(points[j][1] - points[i][1], 2));
                    distanceMap.put(distance, distanceMap.getOrDefault(distance, 0) + 1);

//                    List<String> tempList = printMap.get(distance);
//                    if (Objects.isNull(tempList)) {
//                        printMap.put(distance, tempList = new ArrayList<>());
//                    }
//                    tempList.add(Arrays.toString(points[j]));
                }

                //统计距离组合
                for (Integer count : distanceMap.values()) {
                    num += count * (count - 1);//A(count,2)
                }

//                System.out.println(Arrays.toString(points[i]) + " === " + printMap);
            }
        }

        return num;
    }
}
