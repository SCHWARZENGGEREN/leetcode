package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/5/27 10:46
 * @Description: 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 2^31.
 * <p>
 * 示例:
 * <p>
 * 输入: x = 1, y = 4
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * (     )↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class HammingDistance {

    public static void main(String[] args) {
        int x = 1, y = 3;
        System.out.println(hammingDistance(x, y));
    }

    /**
     * 使用异或算法得到的数字,如果是偶数就一直右移
     * 11
     * 01
     * ^
     * 10
     *
     * @param x
     * @param y
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int hammingDistance(int x, int y) {
        int z = x ^ y, res = 0;
        while (z > 0) {//统计二进制中的1
            if ((z & 1) == 1) res++;
            z >>= 1;
        }
        return res;
    }
}
