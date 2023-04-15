package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.awt.*;
import java.util.Arrays;

/**
 * @author renxinheng
 * @ClassName FlowerPlantingWithNoAdjacent
 * @createTime 2023/4/15
 * @desc 1042. 不邻接植花
 * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 * <p>
 * 示例 1：
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 * <p>
 * 示例 2：
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * <p>
 * 示例 3：
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * 1 <= n <= 10^4
 * 0 <= paths.length <= 2 * 10^4
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园 最多 有 3 条路径可以进入或离开
 */
public class FlowerPlantingWithNoAdjacent {

    public static void main(String[] args) {
        int n = 3;
//        int[][] paths = {{1, 2}, {3, 2}, {1, 3}};

        int[][] paths = {{1,2},{2,3}};
        System.out.println(Arrays.toString(gardenNoAdj(n, paths)));
    }

    /**
     * 回溯
     *
     * @param n
     * @param paths
     * @return
     */
    @Score(time = Score.S.D,memory = Score.S.SS)
    public static int[] gardenNoAdj(int n, int[][] paths) {
        int[] ans = new int[n];
        gardenNoAdj(0, ans, paths);
        return ans;
    }

    /**
     * 穷举出任意正确答案
     *
     * @param flowers   已种花的花园(gardenNum,flowerType)
     * @param paths     花园通道地图
     * @param gardenIdx 第n个花园
     * @return
     */
    public static boolean gardenNoAdj(int gardenIdx, int[] flowers, int[][] paths) {
        if (gardenIdx >= flowers.length) return false;
        int flowerType = 0;
        while (flowerType++ < 4) {
            flowers[gardenIdx] = flowerType;
            if (!checkRepeat(gardenIdx, flowers, paths)) {
                if (gardenIdx + 1 == flowers.length) return true;
                if (gardenNoAdj(gardenIdx + 1, flowers, paths)) return true;
            }
        }
        return false;
    }

    /**
     * 判断花是否重复
     *
     * @param gardenIdx 花园编号
     * @param flowers   种花记录
     * @param paths
     */
    private static boolean checkRepeat(int gardenIdx, int[] flowers, int[][] paths) {
        int flowerType = flowers[gardenIdx];
        for (int[] path : paths) {
            if (path[0] == gardenIdx + 1) {
                if (flowers[path[1] - 1] == flowerType) return true;
            } else if (path[1] == gardenIdx + 1) {
                if (flowers[path[0] - 1] == flowerType) return true;
            }
        }
        return false;//不重复
    }
}
