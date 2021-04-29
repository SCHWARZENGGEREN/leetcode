package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

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
//        System.out.println(mySqrt(2));
//        System.out.println(mySqrt(3));
//        System.out.println(mySqrt(8));
//        System.out.println(mySqrt(18));
//        System.out.println(mySqrt(9999));
        System.out.println(mySqrt(8192));
        System.out.println(Math.pow(8, 0.5));

//        System.out.println(
//                ThreadLocalRandom.current()
//                        .ints(80, 0, 100)
//                        .boxed()
//                        .sorted()
//                        .collect(Collectors.toList())
//        );
    }

    /**
     * 通过二分法查找,一般情况,n^-2 < n/2,不断二分查找即可
     * TODO 精度问题
     * @param x
     * @return
     */
    @Unsettled
    public static int mySqrt(int x) {
        int tmp = x / 2, divide;
        if (tmp <= 1) return tmp <= 0 ? x : 1;
        while ((divide = x / tmp) > tmp || divide < tmp - 1) {
            //除数过小时削减一半;除数过大是增加一半
            tmp = tmp / 2 + (divide < tmp - 1 ? 0 : tmp);
        }

        return divide == tmp ? tmp : tmp - 1;
    }


}
