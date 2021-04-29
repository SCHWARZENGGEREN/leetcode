package com.example.leetcode.other.hanno_tower;

/**
 * @Auther: Rxh
 * @Date: 2019/11/22 10:26
 * @Description: 汉诺塔
 * 有一个由n块积木累成的塔(A),以及可以放置积木的BC塔,他们都没有积木,要求将A所有的积木最终转移到C,
 * 且过程中小的积木块不能放到大的积木块上面
 */
public class HannoTower {

    public static void main(String[] args) {
        int n = 5;
        placeHannoTower(n);
    }

    /**
     * 通过编号记录ABC每层上面的积木,每次移动打印过程
     *
     * @param n A塔上面的层数
     */
    private static void placeHannoTower(int n) {
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];

        for (int i = 1; i <= n; i++) {
            A[i - 1] = i;
        }

    }

    private static void placeHannoTower(int[] A, int[] B, int[] C) {

    }

    private static boolean checkFull(int[] t) {
        for (int i = t.length - 1; i >= 0; i--) {
            if (t[i] == 0) return false;
        }
        return true;
    }


}
