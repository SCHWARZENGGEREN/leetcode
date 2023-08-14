package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @author renxinheng
 * @ClassName CircleAndRectangleOverlapping
 * @createTime 2023/6/25
 * @desc 1401. 圆和矩形是否有重叠
 * 给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
 * 如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（<<<两者都包括点落在边界上的情况>>>）。
 * <p>
 * 示例 1 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * 输出：true
 * 解释：圆和矩形存在公共点 (1,0) 。
 * <p>
 * 示例 2 ：
 * 输入：radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * 输出：false
 * <p>
 * 示例 3 ：
 * 输入：radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * 输出：true
 * <p>
 * 提示：
 * 1 <= radius <= 2000
 * -10^4 <= xCenter, yCenter <= 10^4
 * -10^4 <= x1 < x2 <= 10^4
 * -10^4 <= y1 < y2 <= 10^4
 */
public class CircleAndRectangleOverlapping {

    public static void main(String[] args) {
        int radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1;

        radius = 1415;
        xCenter = 807;
        yCenter = -784;
        x1 = -733;
        y1 = 623;
        x2 = -533;
        y2 = 1005;
//        System.out.println(checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2));
//        System.out.println(checkOverlap1(radius, xCenter, yCenter, x1, y1, x2, y2));
        System.out.println(checkOverlap2(radius, xCenter, yCenter, x1, y1, x2, y2));
    }

    /**
     * 计算两点距离(平方)
     *
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
    private static int calcDistSq(int x, int y, int x2, int y2) {
        return (x - x2) * (x - x2) + (y - y2) * (y - y2);
    }

    /**
     * 数学分析:可以理解为,在矩形中寻找一个点,距离圆心的距离<r,
     * 即是否存在一个最近点(x,y),x:[x1,x2];y:[y1,y2];
     * 那么在这个前提下要在x轴上找到距离最近的x坐标和y轴上距离最近的y坐标,得到的点即是最近的点
     * 核心思维是把x轴和y轴分开分析
     * @param radius
     * @param xCenter
     * @param yCenter
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    @Score(time = Score.S.SSS)
    public static boolean checkOverlap2(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int nearestX = getMinDistance(x1, x2, xCenter);
        int nearestY = getMinDistance(y1, y2, yCenter);
        return nearestX * nearestX + nearestY * nearestY <= radius * radius;
    }

    /**
     * 分为两种情况,1,center在区间范围内,那此时最近的肯定是cener;
     * 2,center不在区间范围,则最近的是区间左or右边界
     *
     * @param min
     * @param max
     * @param center
     * @return
     */
    private static int getMinDistance(int min, int max, int center) {
        if (center >= min && center <= max) {
            return 0;
        }

        return center < min ? min - center : center - max;
    }
}
