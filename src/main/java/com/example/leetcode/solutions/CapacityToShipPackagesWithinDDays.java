package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;

/**
 * @Auther: Rxh
 * @Date: 2021/4/26 10:17
 * @Description: 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * 示例 2：
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * 示例 3：
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 * <p>
 * 提示：
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CapacityToShipPackagesWithinDDays {

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;
        int[] weights1 = {3, 2, 2, 4, 1, 4};
        int D1 = 3;
        int[] weights2 = {1, 2, 3, 1, 1};
        int D2 = 4;
        System.out.println(shipWithinDays1(weights, D));
        System.out.println(shipWithinDays1(weights1, D1));
        System.out.println(shipWithinDays1(weights2, D2));
    }

    /**
     * 讲人话:将顺序固定的一组数字分成若干组,求分组后每组数字之和的最小值
     * <p>
     * {1, 2, 3, 4,  5,  6,  7,  8,  9,  10}
     * {1, 3, 6, 10, 15,  21, 28, 36,  45, 55}
     * <p>
     * [3,2,2,4, 1, 4]
     * [3,5,7,11,12,15]
     * <p>
     * 思路:累计所有货物的重量,假设这些重量按照天数均分,寻找分组中的最大重量总和最接近平均值的方案
     *
     * @param weights
     * @param D
     * @return
     */
    @Unsettled
    public static int shipWithinDays(int[] weights, int D) {
        int dailyMax = 0;


        return dailyMax;
    }

    /**
     * 运载能力一定在最大重量和重量中和之间,使用二分法在最大值和最小值之间查找,
     * 并判断是否能在D天内运完(将货物分组,每组总重量小于当前运载力,如果能在D天内运完,则说明可以)
     *
     * @param weights
     * @param D
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.D)
    public static int shipWithinDays1(int[] weights, int D) {
        int max_L = 0, total_R = 0, midCap;
        for (int weight : weights) {
            max_L = Math.max(max_L, weight);
            total_R += weight;
        }
        int[] min = {total_R};
        while (max_L < total_R) {
            midCap = (max_L + total_R) / 2;
            if (canShip(weights, D, midCap)) {
                //可以运载,继续减小运载量
                total_R = midCap;
            } else {
                //运载量过小
                max_L = midCap + 1;
            }
        }
//        return min[0];
        return max_L;//为什么min = max_L
    }

    public static boolean canShip(int[] weights, int D, int capacity
//            , int[] min
    ) {
        int subWeights = 0, tempMin = 0;
        for (int weight : weights) {
            subWeights += weight;
            if (subWeights > capacity) {
                tempMin = Math.max(tempMin, subWeights - weight);
                subWeights = weight;
                D--;
            }
        }
//        if (D > 0) {
//            min[0] = Math.min(min[0], tempMin);
//            return true;
//        } else {
//            return false;
//        }
        return D > 0;
    }
}
