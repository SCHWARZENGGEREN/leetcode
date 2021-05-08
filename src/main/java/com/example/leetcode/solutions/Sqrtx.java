package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/4/2 14:13
 * @Description: 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sqrtx {

    public static void main(String[] args) {
//        System.out.println(
//                ThreadLocalRandom.current()
//                        .ints(80, 0, 100)
//                        .boxed()
//                        .sorted()
//                        .collect(Collectors.toList())
//        );

        System.out.println(mySqrt(Integer.MAX_VALUE));
        System.out.println(mySqrt(46340 * 46340 - 1));
        System.out.println(mySqrt(999999));
        System.out.println(mySqrt(99999));
        System.out.println(mySqrt(9999));
        System.out.println(mySqrt(999));
        System.out.println(mySqrt(99));
        System.out.println(mySqrt(9));
    }

    /**
     * 先根据二进制取左右边界,然后左右分别开方作为目标数字的左右边界
     * 去他娘的优雅
     * @param x
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int mySqrt(int x) {
        if (x == 0) return 0;
        if (x <= 3) return 1;

        int idx = 30;
        int right = Integer.MAX_VALUE, left = 0, mid, pow;//先寻找x的左右边界
        while (idx >= 0) {
            if (x >= (left = 1 << idx)) break;
            right = left;
            idx--;
        }
        right = right >> ((idx + 1) >> 1);//对x的左右边界开放处理:如果idx是奇数,就前进一位,因为 2^n * 2^n = 2^(2n),要保证idx是偶数
        left = left >> ((idx + 1) >> 1);
        if (right > 46340) right = 46340;//防止溢出
        while (left < right) {
            mid = (right + left + 1) / 2;
            if ((pow = mid * mid) == x || left == mid) return mid;
            if (pow > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }
}
