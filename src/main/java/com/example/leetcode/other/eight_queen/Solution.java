package com.example.leetcode.other.eight_queen;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rxh
 * @Date: 2019/10/28 11:20
 * @Description: 八皇后问题
 */
public class Solution {

    /**
     * 使用回溯算法
     *
     * @param args
     */
    public static void main(String[] args) {
        S s = new S();
        s.searchQueenThrone(0, new HashMap<>());
        s.printResult(10);
//        printDemo();
    }

    static class S {
        /**
         * 这里使用map记录棋盘位置,由于这里的规则王后棋子不能在同一条线上所以适用
         * 每一个map代表一种摆放八王后棋子的结果
         */
        private List<Map<Integer, Integer>> result = new ArrayList<>();

        /**
         * 使用回溯逐步找出所有皇后排列方式
         * 根据规则可知匹配到的一组棋子中任意两个x轴/y轴不冲突且不再一条对角线,因此我们以一行为单位,每层只匹配一个棋子
         * 从一个节点往下找,如果符合不冲突规则,则往下继续找,一直到第八层,
         * 如果找到了不冲突节点,说明这个分支路线是对的,将结果收集起来,移除当前节点并返回上层继续找其他分支
         *
         * @tip 使用x代表横轴, y代表纵轴
         */
        void searchQueenThrone(int y, Map<Integer, Integer> currentMap) {
            if (y >= 8) return;
            for (int x = 0; x < 8; x++) {
                if (currentMap.containsKey(x)) continue;//预校验
                if (!checkQueenConflict1(currentMap, x, y)) {
                    currentMap.put(x, y);
                    if (y >= 7) {
                        result.add(new HashMap<>(currentMap));
                        currentMap.remove(x);
                        break;
                    }
                    searchQueenThrone(y + 1, currentMap);
                    currentMap.remove(x);
                }
            }
        }
        //优化,y轴不会冲突,x轴提前校验,这里只管对角线
        boolean checkQueenConflict1(Map<Integer, Integer> queens, int x, int y) {
            for (Map.Entry<Integer, Integer> entry : queens.entrySet()) {
                if (Math.abs(entry.getKey() - x) == Math.abs(entry.getValue() - y)) return true;
            }
            return false;
        }

        boolean checkQueenConflict(Map<Integer, Integer> queens, int x, int y) {
            for (Map.Entry<Integer, Integer> entry : queens.entrySet()) {
                Integer _x = entry.getKey();
                Integer _y = entry.getValue();
                if (_x == x || _y == y || Math.abs(_x - x) == Math.abs(_y - y)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 打印结果,5个一行
         */
        void printResult(int rowCount) {
            int offset = 1, resultSize = result.size();
            while (offset <= resultSize) {
                int matrixCount = Math.min(rowCount, resultSize - (offset - 1));//本次循环数列的数量
                for (int j = 0; j < 8; j++) {//y
                    for (int i = 1; i <= 8 * matrixCount; i++) {//x
                        Map<Integer, Integer> currentRes = result.get(offset - 1 + (i - 1) / 8);
                        boolean currentIsQueen = isQueen(currentRes, (i - 1) % 8, j);
                        System.out.print(" " + (currentIsQueen ? "O" : "+") + (i % 8 == 0 ? " " : ""));
                    }
                    System.out.println();
                }
                System.out.println();
                offset += rowCount;
            }
            System.out.println("共" + result.size() + "种摆放方式");
        }

        boolean isQueen(Map<Integer, Integer> res, int x, int y) {
            for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
                if (entry.getKey() == x && entry.getValue() == y) return true;
            }
            return false;
        }
    }

    static class Pos {
        int x;
        int y;
        boolean isQ;

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
