package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

/**
 * @Auther: Rxh
 * @Date: 2021/4/6 10:37
 * @Description: 题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一个扔入海中，拿走了一份。
 * 第二只猴子把剩下的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，第三、第四、第五只猴子都是这样做的，
 * 问海滩上原来最少有多少个桃子？
 */
public class MonkeyDividePeaches {

    public static void main(String[] args) {
        System.out.println(getPeaches1(5));
//        System.out.println((12496 - 1) / 5 * 4);
//        System.out.println((9996 - 1) / 5 * 4);
//        System.out.println((7996 - 1) / 5 * 4);
//        System.out.println((6396 - 1) / 5 * 4);
//        System.out.println((5116 - 1) / 5 * 4);
    }

    /**
     * @param no
     * @param num
     * @return
     */
    private static int getPeaches(int no, int num) {
        int res = num;
        while (no-- > 0) {
            res = res / 4 * 5 + 1;
        }
        return res;
    }

    /**
     * 每次计算的数字都要满足:n%4==0 && n>=4
     *
     * @param no
     * @return
     */
    @Unsettled
    private static int getPeaches1(int no) {
        int base = 0, res = 0;
        while (res <= 0) {
            res = (base += 4);
            int no1 = no;
            //向上累计,如果发现res%4>0就终止,然后重新计算起始数
            while (no1-- > 0) {
                res = res / 4 * 5 + 1;
                if (res % 4 != 0) {
                    res = -1;
                    break;
                }
            }
            if (res > 0) return res;
        }
        return res;
    }
}
