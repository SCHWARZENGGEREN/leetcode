package com.example.leetcode.algorithm;

import com.example.leetcode.utils.ArrayUtil;
import com.example.leetcode.utils.Util;

/**
 * @author renxinheng
 * @ClassName IslandInundation
 * @createTime 2023/8/8
 * @desc 海岛淹没算法
 * 假设用一个二维数组表示海洋和陆地:
 * 0 0 0 0 0 0
 * 0 1 1 0 0 0
 * 0 1 1 0 0 0
 * 0 0 0 1 1 0
 * 0 0 1 1 1 0
 * 0 0 0 1 1 0
 * 0 0 0 0 0 0
 * 由于气候变化海平面上升,紧邻海洋的陆地都会淹没,只有四周都是陆地的会保留,
 * 计算出剩余的陆地(岛屿)数量
 * <p>
 * 说明:1,
 */
public class IslandInundation {

    public static void main(String[] args) {
        int[][] islands = {
                {0, 0, 0, 0, 1, 0},//
                {0, 0, 0, 1, 1, 1},//
                {0, 0, 0, 0, 1, 0},//
                {1, 1, 0, 0, 1, 1},//
                {0, 1, 0, 0, 1, 0},//
                {1, 1, 0, 0, 1, 1} //
        };
        int[][] realIslands = explodeArray(islands, 2);
        ArrayUtil.printMultiArray(realIslands, i -> {
            if (i == 0) return "~";
            if (i == 1) return "#";
            return String.valueOf(i);
        });
        Util.calcInvokeTime(o -> survivingIslands(realIslands));
    }

    private static int[][] explodeArray(int[][] array, int rate) {
        int depth = array.length;
        int width = array[0].length;
        int[][] bigArr = new int[depth * rate][width * rate];

        for (int r = 0; r < rate; r++) {
            //横向扩展
            for (int j = 0; j < depth; j++) {
                System.arraycopy(array[j], 0, bigArr[j], width * r, width);
            }
        }
        for (int r = 1; r < rate; r++) {
            //纵向扩展
            for (int j = 0; j < depth; j++) {
                System.arraycopy(bigArr[j], 0, bigArr[j + r * depth], 0, width * rate);
            }
        }
        return bigArr;
    }

    /**
     * 标记紧邻海水的陆地,然后统计未标记的陆地数量
     *
     * @param islands
     * @return
     */
    private static int survivingIslands(int[][] islands) {
        int depth = islands.length;
        int width = islands[0].length;
        //1,标记并统计周围没有海水的陆地
        int islandCount = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                if (islands[i][j] != 1) continue;
                if ((i > 0 && islands[i - 1][j] == 0)  //前
                        || (j > 0 && islands[i][j - 1] == 0) //左
                        || (i < depth - 1 && islands[i + 1][j] == 0) //后
                        || (j < width - 1 && islands[i][j + 1] == 0) //右
                ) {
                    islands[i][j] = 2;
                } else {
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    /**
     *
     * @param islands
     * @return
     */
    private static int survivingIslandsDFS(int[][] islands) {

        return 0;
    }
}
