package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/11/21 14:00
 * @Description: 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3};
        System.out.println(removeDuplicates1(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 简单粗暴
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SS)
    public static int removeDuplicates(int[] nums) {
        if (nums != null && nums.length > 0) {
            int unDuplicateCount = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    //出现了不重复的数字,向前归并
                    if (i > unDuplicateCount) {
                        nums[unDuplicateCount] = nums[i];
                    }
                    unDuplicateCount++;
                }
            }
            return unDuplicateCount;
        }
        return 0;
    }

    /**
     * 累计偏移量,根据偏移量移动数字
     *
     * @param nums
     * @return
     * @see RemoveDuplicatesFromSortedArrayIi#removeDuplicates(int[])
     */
    @Score(time = Score.S.S, memory = Score.S.B)
    public static int removeDuplicates1(int[] nums) {
        int duplicateNum = nums[0], shift = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == duplicateNum) {
                shift++;
                continue;//遗弃重复数字
            } else {
                duplicateNum = nums[i];
            }
            //向前移动(重复超过2个的不处理)
            if (shift > 0) nums[i - shift] = nums[i];
        }

        int[] newNums = new int[nums.length - shift];
        System.arraycopy(
                nums,
                0,
                newNums,
                0,
                nums.length - shift);
        System.out.println(Arrays.toString(newNums));

        return nums.length - shift;
    }
}
