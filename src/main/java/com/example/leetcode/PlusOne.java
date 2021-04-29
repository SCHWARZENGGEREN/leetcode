package com.example.leetcode;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/3/30 15:25
 * @Description: 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *  
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] digits = new int[]{9, 9, 9, 9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    /**
     * 计算进位数
     *
     * @param digits
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static int[] plusOne(int[] digits) {
        int carryNumber = 1, add;
        for (int i = digits.length - 1; i >= 0; i--) {
            add = carryNumber + digits[i];
            carryNumber = add > 9 ? 1 : 0;
            digits[i] = add > 9 ? add - 10 : add;
        }
        if (carryNumber > 0) {
            //位数扩展
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = carryNumber;
            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            return newDigits;
        }

        return digits;
    }
}
