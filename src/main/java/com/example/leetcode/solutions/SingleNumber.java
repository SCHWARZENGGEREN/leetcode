package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2020/5/14 10:30
 * @Description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] ints = {2, 3, 1, 2, 3, 1, 5};
        System.out.println(singleNumber2(ints));
    }

    /**
     * simple
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = nums[0];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            boolean noneRepeat = true;
            for (int j = 0; j < length; j++) {
                if (i == j) continue;
                if (nums[i] == nums[j]) {
                    noneRepeat = false;
                    break;
                }
            }
            if (noneRepeat) {
                result = nums[i];
                break;
            }
        }
        return result;
    }

    /**
     * 递归算法,在数据量大的情况下,效率反而比循环算法低,而且耗内存
     *
     * @param nums
     * @return
     */
    public static int singleNumber1(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (findRepeatIdx(0, i, nums) < 0) {
                return nums[i];
            }
        }
        return nums[0];
    }

    public static int findRepeatIdx(int current, int i, int[] nums) {
        if (current == nums.length) return -1;
        if (current != i) {
            if (nums[current] == nums[i]) {
                return current;
            }
        }
        return findRepeatIdx(current + 1, i, nums);
    }


    /**
     * 直接从二进制位角度考虑
     * 异或算法,对比每个二进制位,不同为1,相同为0,用0依次异或运算数组每一个值,相同的数字一一抵消,剩下的就是唯一的那个
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int base = 0;
        for (int i = 0; i < nums.length; i++) {
            base ^= nums[i];
        }
        return base;
    }

}
