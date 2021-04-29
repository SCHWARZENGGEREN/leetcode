package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/4/8 15:52
 * @Description: 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums =    [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * <p>
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * <p>
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 * <p>
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * <p>
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin1(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 题目描述方式略难懂,其实就是给一个可能旋转过的数组,然后找出其最小值,并将数组旋转为顺序
     * <p>
     * [4,5,6,7, 0,1,2] 1,找到断点
     * [0,1,2, 7,4,5,6] 2,将断点后面的跟前面等长兑换
     * [0,1,2, 4,5,6,7] 3,将中间剩下的挪到后面
     * <p>
     * [4,5,6,7, 0,1,2] 1,找到断点
     * [0,1,2,7, 6,5,4] 2,将断点后面的数字对称置换到前面
     * [2,1,0, 7,6,5,4] 3,根据长度找出新的断点
     * [0,1,2, 4,5,6,7] 4,分别将新断点前后两端颠倒
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int min = nums[0];
        if (nums.length == 1) return min;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
                break;
            }
        }
        return min;
    }

    /**
     * 找到断点,并
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    public static int findMin1(int[] nums) {
        if (nums.length > 1 && nums[0] > nums[nums.length - 1]) {
            //寻找断点,并重组两部分数据
            int breakIdx = 0;//breakIdx以及后面的平移到数组前面
            int[] tmp = new int[nums.length];
            int type = 1;//1,寻找断点;2,重排
            for (int i = 0; i < nums.length; i++) {//因为有螺旋出现,因此到最后是type一定为2
                if (type == 1) {
                    tmp[i] = nums[i];
                    if (nums[i] > nums[i + 1]) {
                        breakIdx = i;
                        type = 2;
                    }
                } else {
                    nums[i - breakIdx - 1] = nums[i];
                }
            }
            for (int i = nums.length - 1, j = breakIdx; j >= 0; i--, j--) {
                nums[i] = tmp[j];
            }
        }
        return nums[0];
    }
}
