package com.example.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Rxh
 * @Date: 2020/5/14 15:25
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SingleNumberII {

    public static void main(String[] args) {
        System.out.println(0 ^ 0);
        System.out.println(1 ^ 1);
        System.out.println(0 & 0);
        System.out.println(1 & 1);
        System.out.println(0 | 0);
        System.out.println(1 | 1);

        int[] ints = {2, 3, 1, 2, 3, 5};
        System.out.println(singleNumber1(ints));
    }

    /**
     * TODO 使用位运算? 使用异或运算,出现三次的数字异或运算后还是本身,那么增加一次或者减少一次异或运算
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {

        return nums[0];
    }

    /**
     * 暴力破解法
     * @param nums
     * @return
     */
    public static int singleNumber1(int[] nums) {
        if (nums.length <= 3) return nums[0];
        Set<Integer> numSet = new HashSet<>();
        boolean[] duplicates = new boolean[nums.length];
        return 0;
    }
}
