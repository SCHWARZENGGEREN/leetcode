package com.example.leetcode.other;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/8/31 10:25
 * @Description: 1109. 航班预订统计
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * <p>
 * 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * 示例 2：
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2 * 104
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 10^4
 */
public class CorporateFlightBookings {

    public static void main(String[] args) {
        int n = 5;
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        System.out.println(Arrays.toString(corpFlightBookings(bookings, n)));
        System.out.println(Arrays.toString(corpFlightBookings1(bookings, n)));
    }

    /**
     * 暴力统计法,时间复杂度n(1-n)
     *
     * @param bookings
     * @param n
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.A)
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] flights = new int[n];
        int i = 0;
        for (int[] booking : bookings) {
            i = booking[0];
            while (i <= booking[1]) {
                flights[i - 1] += booking[2];
                i++;
            }
        }

        return flights;
    }

    /**
     * 把航班当做公交车次,
     * 在每个区间起始站加上上车的人,然后在区间结束后第一个站减去下车的人,最后按照每个区间始末站统计算所有站人数
     * 复杂度2n
     *
     * @param bookings
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.C)
    public static int[] corpFlightBookings1(int[][] bookings, int n) {
        int[] flights = new int[n];
        for (int[] booking : bookings) {
            flights[booking[0] - 1] += booking[2];//区间开始点累加上车的人
            if (booking[1] < n) {//上车的人没坐到终点,区间结束后的第一个站点减去下车的人
                flights[booking[1]] -= booking[2];
            }
        }
        //统计完每个区间起始点结束点人数,按照这两个点累算所有站点人数
        int i = 1;
        while (i < n) {
            flights[i] += flights[i - 1];
            i++;
        }

        return flights;
    }
}
