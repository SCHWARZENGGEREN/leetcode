package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/8/29 15:53
 * @Description: 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class ReverseInteger {

    public static void main(String[] args) {
//        System.out.println(S1.reverse(-1236587451));
//        System.out.println(S2.reverse(-1236587452));
        System.out.println(S3.reverse(-1563847412));

        System.exit(0);
    }

    private static class S1 {
        /**
         * 简单的方法,先转成long,然后转成int
         * 效率较低
         *
         * @param x
         * @return
         */
        static int reverse(int x) {
            Long res = null;
            if (x != 0) {
                if (x > -10 && x < 10) return x;
                if (x == Integer.MIN_VALUE) return 0;
                boolean isPos = x > 0;
                StringBuilder sb = new StringBuilder();
                sb.append(x);
                if (!isPos) {
                    sb = sb.reverse();
                    sb.setLength(sb.length() - 1);
                    res = Long.valueOf(sb.toString()) * -1;
                } else {
                    res = Long.valueOf(sb.reverse().toString());
                }
            }
            return res == null || res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) (res.longValue());
        }
    }

    private static class S2 {

        /**
         * 不使用字符串,直接使用数字
         *
         * @param x
         * @return
         */
        @Score(time = Score.S.SS, memory = Score.S.S)
        static int reverse(int x) {
            if (x >= 10 || x <= -10) {
                if (x == Integer.MIN_VALUE) return 0;
                boolean isNeg = x < 0;
                if (isNeg) x *= -1;
                long res = 0;
                int dig = 10;
                while (x > 0) {
                    res = res * dig + x % dig;
                    x /= dig;
                }
                if (isNeg) res *= -1;
                return (int) (res <= Integer.MAX_VALUE && res >= Integer.MIN_VALUE ? res : 0);
            }
            return x;
        }
    }

    static class S3 {

        /**
         * 不适用字符串,不使用long
         *
         * @param x
         * @return
         */
        static int reverse(int x) {
            if (x >= 10 || x <= -10) {
                if (x == Integer.MIN_VALUE) return 0;
                boolean isNeg = x < 0;
                if (isNeg) x *= -1;
                int res = 0;
                int dig = 10;
                while (x != 0) {
                    if (x < 10 && x > -10) {
                        //校验超限
                        int limit = (Integer.MAX_VALUE - x % dig) / dig;
                        if ((!isNeg && res > limit) || (isNeg && res > ++limit)) {
                            //TODO 精度丢失
                            return 0;
                        }
                    }
                    res = res * dig + x % dig;
                    x /= dig;
                }
                if (isNeg) res *= -1;
                return res;
            }
            return x;
        }
    }
}
