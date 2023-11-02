package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @author renxinheng
 * @ClassName RingsAndRods
 * @createTime 2023/11/2
 * @desc 2103. 环和杆
 * 总计有 n 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 0 到 9 的杆上。
 * 给你一个长度为 2n 的字符串 rings ，表示这 n 个环在杆上的分布。rings 中每两个字符形成一个 颜色位置对 ，用于描述每个环：
 * 第 i 对中的 第一个 字符表示第 i 个环的 颜色（'R'、'G'、'B'）。
 * 第 i 对中的 第二个 字符表示第 i 个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
 * 例如，"R3G2B1" 表示：共有 n == 3 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。
 * 找出所有集齐 全部三种颜色 环的杆，并返回这种杆的数量。
 * &lt;height>185&lt;/height>
 * 示例 1：
 * <img src="/images/leecode/2103_rings-and-rods.png" width="111" height="55"/>
 * <p>
 * 输入：rings = "B0B6G0R6R0R6G9"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
 * - 编号 9 的杆上只有 1 个绿色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * <p>
 * 示例 2：
 * 输入：rings = "B0R0G0R9R0B0G0"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 9 的杆上只有 1 个红色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * <p>
 * 示例 3：
 * 输入：rings = "G4"
 * 输出：0
 * 解释：
 * 只给了一个环，因此，不存在集齐全部三种颜色环的杆。
 * <p>
 * 提示：
 * rings.length == 2 * n
 * 1 <= n <= 100
 * 如 i 是 偶数 ，则 rings[i] 的值可以取 'R'、'G' 或 'B'（下标从 0 开始计数）
 * 如 i 是 奇数 ，则 rings[i] 的值可以取 '0' 到 '9' 中的一个数字（下标从 0 开始计数）
 */
public class RingsAndRods {

    public static void main(String[] args) {
        System.out.println(countPoints2("B0B6G0R6R0R6G9"));
        System.out.println(countPoints3("B0B6G0R6R0R6G9"));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(234));
//        System.out.println(Integer.toBinaryString(234434));
//        System.out.println(1 << 3);
    }

    /**
     * 偶数位是环,奇数位是杆
     * 直接暴力统计
     *
     * @param rings
     * @return
     */
    @Score(time = Score.S.E, memory = Score.S.E)
    public static int countPoints(String rings) {
        Map<Character, Set<Character>> statisticMap = new HashMap<>();
        for (int i = 1; i < rings.toCharArray().length; i += 2) {
            if (!statisticMap.containsKey(rings.charAt(i))) {
                statisticMap.put(rings.charAt(i), new HashSet<>());
            }
            statisticMap.get(rings.charAt(i)).add(rings.charAt(i - 1));
        }
        return (int) statisticMap.values().stream().filter(Objects::nonNull)
                .filter(set -> set.size() >= 3)
                .count();
    }

    /**
     * 以二维数组统计每个杆子上的数量
     * 还有优化空间吗?
     *
     * @param rings
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.D)
    public static int countPoints1(String rings) {
        int[][] points = new int[10][3];
        for (int i = 1; i < rings.toCharArray().length; i += 2) {
            points[rings.charAt(i) - '0'][getColorIdx(rings.charAt(i - 1))]++;
        }
        int count = 0;
        for (int[] point : points) {
            if (point[0] > 0 && point[1] > 0 && point[2] > 0)
                count++;
        }
        return count;
    }

    /**
     * 进一步优化,用一个数字表示
     *
     * @param rings
     * @return
     */
    @Score(time = Score.S.B, memory = Score.S.D)
    public static int countPoints2(String rings) {
        int[] points = new int[10];
        int count = 0;
        for (int i = 1; i < rings.toCharArray().length; i += 2) {
            int idx = rings.charAt(i) - '0';
            points[idx] = calcColor(points[idx], rings.charAt(i - 1));
        }
        for (int point : points) {
            if (point > 1000000 && (point %= 1000000) > 1000 && (point %= 1000) >= 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 无法跨越的阶层
     * R = 1
     * G = 1000
     * B = 1000000
     *
     * @param sum
     * @param color
     * @return
     */
    private static int calcColor(int sum, char color) {
        if (color == 'R') {
            //1
            return sum + 1;
        } else if (color == 'G') {
            //10
            return sum + 1000;
        } else {
            //100
            return sum + 1000000;
        }
    }

    private static int getColorIdx(char color) {
        return color == 'R' ? 0 :
                color == 'G' ? 1 : 2;
    }

    /**
     * 位运算 用三个数字的2进制位表示对应的位置的杆子上三种颜色有无
     * 最后与运算
     *
     * @param rings
     * @return
     */
    @Score(time = Score.S.B, memory = Score.S.D)
    public static int countPoints3(String rings) {
        int R = 0, G = 0, B = 0, count = 0;
        for (int i = 1; i < rings.toCharArray().length; i += 2) {
            char color = rings.charAt(i - 1);
            int digNum = 1 << (rings.charAt(i) - '0');
            if (color == 'R') {
                R |= digNum;
            } else if (color == 'G') {
                G |= digNum;
            } else {
                B |= digNum;
            }
            System.out.printf("R: %s; G: %s; B: %s %n", Integer.toBinaryString(R), Integer.toBinaryString(G), Integer.toBinaryString(B));
        }
        System.out.println("RES: " + Integer.toBinaryString(R & G & B));
        for (char c : Integer.toBinaryString(R & G & B).toCharArray()) {
            if (c == '1') count++;
        }
        return count;
    }

    /**
     * 差距这么大吗
     * @param s
     * @return
     */
    @Score(time = Score.S.FULL, memory = Score.S.A, way = Score.WAY.COPIED)
    public int countPoints4(String s) {
        int n = s.length(), ans = 0;
        int[] map = new int[128];
        for (int i = 0; i < n; i += 2) {
            map[s.charAt(i) - 'B'] |= 1 << (s.charAt(i + 1) - '0');
        }
        for (int i = 0; i < 10; i++) {
            int tot = 0;
            for (char c : new char[]{'R', 'G', 'B'}) tot += (map[c - 'B'] >> i) & 1;
            if (tot == 3) ans++;
        }
        return ans;
    }
}
