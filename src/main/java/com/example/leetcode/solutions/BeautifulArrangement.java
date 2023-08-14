package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2021/8/16 14:48
 * @Description: 526. 优美的排列
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N)
 * 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * <p>
 * 1,第 i 位的数字能被 i 整除
 * 2,i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * <p>
 * 示例1:
 * 输入: 2
 * 输出: 2
 * 解释:
 * 第 1 个优美的排列是 [1, 2]:
 * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * <p>
 * 第 2 个优美的排列是 [2, 1]:
 * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * 说明:
 * <p>
 * N 是一个正整数，并且不会超过15。
 */
public class BeautifulArrangement {


    public static void main(String[] args) {
        System.out.println(countArrangement(4));
    }

    /**
     * 数列所有数字满足: i能被arr[i]整除 or
     * arr[i]能被i整除就可以
     * <p>
     * 本质是匹配数字对,对两组1-n的数字进行不重复配对,要求每一对满足上面条件之一即可
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.D,memory = Score.S.SS)
    public static int countArrangement(int n) {
        int[] count = new int[1];
        List<Integer> numArray = new ArrayList<>(n);
        count(n, count, numArray, 0);
        return count[0];
    }

    public static void count(int num, int[] count, List<Integer> numArray, int idx) {
        if (idx >= num) {
            count[0]++;//匹配成功
            System.out.println(numArray);
            return;
        }
        int temp = num;
        while (temp > 0) {
            if (!numArray.contains(temp) && check(idx, temp)) {
                numArray.add(temp);
                count(num, count, numArray, idx + 1);
                numArray.remove(idx);
            }
            temp--;
        }
    }

    public static boolean check(int idx, int num) {
        return ++idx >= num ? idx % num == 0 : num % idx == 0;
    }


}
