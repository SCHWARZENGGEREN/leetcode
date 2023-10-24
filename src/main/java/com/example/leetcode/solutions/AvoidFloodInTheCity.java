package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @author renxinheng
 * @ClassName AvoidFloodInTheCity
 * @createTime 2023/10/13
 * @desc 1488. 避免洪水泛滥
 * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨前是空的，那么它就会装满水。
 * 如果第 n 个湖泊下雨前是 满的 ，这个湖泊会发生 洪水 。你的目标是避免任意一个湖泊发生洪水。
 * 给你一个整数数组 rains ，其中：
 * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
 * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
 * 请返回一个数组 ans ，满足：
 * <p>
 * ans.length == rains.length
 * 如果 rains[i] > 0 ，那么ans[i] == -1 。
 * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
 * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
 * <p>
 * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
 * <p>
 * 示例 1：
 * 输入：rains = [1,2,3,4]
 * 输出：[-1,-1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，装满水的湖泊包括 [1,2,3]
 * 第四天后，装满水的湖泊包括 [1,2,3,4]
 * 没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
 * <p>
 * 示例 2：
 * 输入：rains = [1,2,0,0,2,1]
 * 输出：[-1,-1,2,1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
 * 第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
 * 第五天后，装满水的湖泊包括 [2]。
 * 第六天后，装满水的湖泊包括 [1,2]。
 * 可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
 * <p>
 * 示例 3：
 * 输入：rains = [1,2,0,1,2]
 * 输出：[]
 * 解释：第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。
 * 但第三天后，湖泊 1 和 2 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。
 * <p>
 * 提示：
 * 1 <= rains.length <= 10^5
 * 0 <= rains[i] <= 10^9
 */
public class AvoidFloodInTheCity {

    public static void main(String[] args) {
        int[] rains = {1, 0, 2, 0, 3, 0, 2, 0, 0, 0, 1, 2, 3};
//        int[] rains = {0,1,1};
        System.out.println(Arrays.toString(avoidFlood(rains)));
    }

    /**
     * 不下雨的日子可以抽干湖水
     * 标记不下雨的每一天至少需要抽水的湖泊数量,如果有某一天数量>1,说明无法避免洪水
     * todo 优化效率
     * @param rains
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.S)
    public static int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        //初始化
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] > 0) {
                ans[i] = -1;
            }
        }
        //记录一个湖泊最后一次下雨的日期
        TreeSet<Integer> rainyDays = new TreeSet<>();
        Map<Integer, Integer> rainMap = new HashMap<>();
        boolean hasDays = false;
        for (int i = 0; i < rains.length; i++) {
            int poorNum = rains[i];
            if (poorNum > 0) {
                //下雨了
                Integer recentRainDay = rainMap.get(poorNum);
                if (Objects.nonNull(recentRainDay)) {
                    //湖泊下过雨 需要往前找一天抽水
                    int idx = recentRainDay;
                    hasDays = false;
                    //就近选日期抽水
                    while (++idx < i) {
                        if (ans[idx] == 0) {
                            //可以抽水 就近抽呗
                            ans[idx] = poorNum;
                            hasDays = true;
                            break;
                        }
                    }
                    if (!hasDays) {
                        //无法避免洪水
                        return new int[0];
                    }
                }
                //刷新下雨天数
                rainMap.put(poorNum, i);
            }
        }
        //看看哪些可以抽水但是没抽的
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) {
                ans[i] = 1;
            }
        }
        //标记所有湖水
        return ans;
    }

    /**
     * 用TreeSet 二分查找优化
     * @param rains
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.SS)
    public static int[] avoidFlood1(int[] rains) {
        int[] ans = new int[rains.length];
        //记录一个湖泊最后一次下雨的日期
        TreeSet<Integer> rainyDays = new TreeSet<>();
        Map<Integer, Integer> rainMap = new HashMap<>();
        boolean hasDays = false;
        for (int i = 0; i < rains.length; i++) {
            int poorNum = rains[i];
            if (poorNum == 0) {
                //没下雨
                rainyDays.add(i);
                continue;
            }
            //下雨了
            Integer recentRainDay = rainMap.get(poorNum);
            if (Objects.nonNull(recentRainDay)) {
                //湖泊下过雨 需要往前找一天抽水
                int idx = recentRainDay;
                hasDays = false;
                //就近选日期抽水
                while (++idx < i) {
                    if (ans[idx] == 0) {
                        //可以抽水 就近抽呗
                        ans[idx] = poorNum;
                        hasDays = true;
                        break;
                    }
                }
                if (!hasDays) {
                    //无法避免洪水
                    return new int[0];
                }
            }
            //刷新下雨天数
            rainMap.put(poorNum, i);
        }
        //看看哪些可以抽水但是没抽的
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) {
                ans[i] = 1;
            }
        }
        //标记所有湖水
        return ans;
    }
}
