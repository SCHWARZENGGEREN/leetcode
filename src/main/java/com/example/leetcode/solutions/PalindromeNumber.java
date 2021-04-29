package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2019/9/4 16:02
 * @Description: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(S1.isPalindrome(11));
    }

    public static class S1 {
        static boolean isPalindrome(int x) {
            int dig = 10;
            if (x < 0) return false;//负数全部pass
            if (x < dig) return true;//0-9全部符合
            if (x % dig == 0) return false;//10的整倍数pass

            int[] digs = new int[10];
            int idx = 0;
            while (x > 0 && x / dig >= 0) {
                digs[idx] = x%dig;
                x/=dig;
                idx++;
            }

            for (int i = 0;i<idx/2;i++){
                if (digs[i] != digs[idx-1-i]){
                    return false;
                }
            }
            return true;
        }
    }
}
