package com.example.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/3/8 10:18
 * @Description: 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * TIPS:
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 * 1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BusRoutes {

    public static void main(String[] args) {
        int[][] routes = {
                {1, 2, 7},
                {3, 6, 7}
        };
        int source = 1, target = 7;
        System.out.println(numBusesToDestination(routes, source, target));
    }

    /**
     * 将每条线路视为一个点,如果两条线路之间有交集,则两个点之间有连线;
     * 将出发点和截止点也作为两个点,如果某个线路包含出发点或者截止点,就给它们之间连线;
     * 那么问题就变成:找寻出发点和截止点之间最短连线的策略
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public static int numBusesToDestination1(int[][] routes, int source, int target) {
        int len = routes.length;
        //给每个线路排序
        if (routes.length > 1){

        }
        return 0;
    }
    /**
     * 逆推法:
     * 第n个台阶只能从第n-1或者n-2个上来。
     * 到第n-1个台阶的走法 + 第n-2个台阶的走法 = 到第n个台阶的走法，已经知道了第1个和第2个台阶的走法，一路加上去。
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public static int numBusesToDestination2(int[][] routes, int source, int target) {
        int len = routes.length;
        //给每个线路排序
        if (routes.length > 1){

        }
        return 0;
    }

    /**
     * 最短换乘方案
     * 使用回溯统计最短线路
     * 先找出出发点车站和到站点车站的线路列表,然后一一匹配:
     * 匹配之后在以出发点下一站和到站点前一站作为首位点进行匹配...
     * 如果匹配成功则计算换乘车次数量,没成返回-1...
     * 取换乘数最小值
     *
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (target == source)
            return 1;
        if (routes.length > 0) {
            List<Integer> sourceRouteIndexes = new ArrayList<>();
            List<Integer> targetRouteIndexes = new ArrayList<>();
            for (int[] route : routes) {
                int i = route.length;
                while (i-- > 0) {
                    if (route[i] == source) {

                        sourceRouteIndexes.add(route[i]);
                    }
                    if (route[i] == target) {
                        targetRouteIndexes.add(route[i]);
                    }
                }
            }
            if (sourceRouteIndexes.size() > 0 && targetRouteIndexes.size() > 0) {
                return findMinRoute(routes, source, target, 0, Integer.MAX_VALUE);
            }
        }
        return -1;
    }

    /**
     *
     * @param routes
     * @param source
     * @param target
     * @param currentRoutes 当前换成数
     * @param minRoute 最小换乘数
     * @return
     */
    private static int findMinRoute(int[][] routes, int source, int target, int currentRoutes, int minRoute) {

        return minRoute;
    }
}
