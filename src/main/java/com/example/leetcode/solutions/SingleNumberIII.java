package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2020/5/14 15:25
 * @Description: 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * <p>
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * <p>
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 */
public class SingleNumberIII {

    public static void main(String[] args) {
        System.out.println(0 ^ 0);
        System.out.println(1 ^ 1);
        System.out.println(0 & 0);
        System.out.println(1 & 1);
        System.out.println(0 | 0);
        System.out.println(1 | 1);
        System.out.println(1 & (-1));

//        int[] ints = {2, 3, 1, 2, 3, 5};
//        System.out.println(singleNumber(ints));
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {
        int result = nums[0];
        int length = nums.length;
        int[] res = new int[2];
        //先位运算总值
        return res;
    }
}
