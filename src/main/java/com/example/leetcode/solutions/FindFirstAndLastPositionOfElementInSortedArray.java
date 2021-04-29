package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.ToImprove;


/**
 * @Auther: Rxh
 * @Date: 2019/11/12 10:05
 * @Description: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
@ToImprove
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {6, 6, 6, 6, 6};
        int target = 6;
//        System.out.println(Arrays.toString(searchRange1(nums, target)));
//        System.out.println(Arrays.toString(searchRange2(nums, target)));
//        System.out.println(Arrays.toString(searchRange3(nums, target)));
//        System.out.println(Arrays.toString(searchRange4(nums, target)));
    }

    /**
     * 普通方法
     * <p>
     * 逻辑简单,空间复杂度低,但是效率不够
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.SS)
    public static int[] searchRange1(int[] nums, int target) {
        int[] result = {-1, -1};
        int len = nums.length, count = 0;
        if (len > 0 && target >= nums[0] && target <= nums[len - 1]) {
            for (int i = 0; i < len; i++) {
                if (result[0] > -1) {
                    if (nums[i] == nums[i - 1]) {
                        count++;
                    } else {
                        break;
                    }
                } else {
                    if (nums[i] == target) {
                        result[0] = i;
                        result[1] = i;
                    } else if (nums[i] > target) {
                        break;
                    }
                }
            }
            result[1] = result[1] + count;
        }
        return result;
    }

    /**
     * 使用二分法更,无需遍历整个数组
     * 校验麻烦,效率与直接遍历相差不多
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.SS)
    public static int[] searchRange2(int[] nums, int target) {
        int[] result = {-1, -1};
        int len = nums.length;
        if (len > 0 && target >= nums[0] && target <= nums[len - 1]) {
            binarySearchRange(nums, target, result, 0, len - 1, (len - 1) / 2);
            if (result[0] > -1) {
                for (int i = result[0] + 1; i < len; i++) {
                    if (nums[i] == target) {
                        result[1] += 1;
                    }
                }
                for (int i = result[0] - 1; i >= 0; i--) {
                    if (nums[i] == target) {
                        result[0] -= 1;
                    }
                }
            }
        }
        return result;
    }

    public static void binarySearchRange(int[] nums, int target, int[] result, int start, int end, int middle) {
        if (nums[start] == target) {
            result[0] = start;
            result[1] = start;
            return;
        }
        if (nums[end] == target) {
            result[0] = end;
            result[1] = end;
            return;
        }
        if (nums[middle] == target) {
            result[0] = middle;
            result[1] = middle;
            return;
        }
        if (middle > start) {
            if (nums[middle] > target) {
                end = middle;
            } else {
                start = middle;
            }
            middle = (start + end) / 2;
            binarySearchRange(nums, target, result, start, end, middle);
        }
    }

    /**
     * 使用双指针
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.A)
    public static int[] searchRange3(int[] nums, int target) {
        int[] result = {-1, -1};
        int len = nums.length, left = 0, right = len - 1, mark = 0;
        if (len > 0 && target >= nums[0] && target <= nums[len - 1]) {
            //用双指针逐渐向中间靠近,一旦某个指针发现目标数,则开始统计,统计完结束遍历
            while (left <= right) {
                if (nums[left] == target) {
                    result[0] = left;
                    result[1] = left;
                    mark = 1;
                    break;
                } else if (nums[left] > target) {
                    break;
                } else {
                    left++;
                }
                if (nums[right] == target) {
                    result[0] = right;
                    result[1] = right;
                    mark = -1;
                    break;
                } else if (nums[right] < target) {
                    break;
                } else {
                    right--;
                }
            }
            if (mark > 0) {
                for (int i = result[0] + 1; i < len; i++) {
                    if (nums[i] == target) {
                        result[1] += 1;
                    } else {
                        break;
                    }
                }
            } else if (mark < 0) {
                for (int i = result[0] - 1; i >= 0; i--) {
                    if (nums[i] == target) {
                        result[0] -= 1;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 双指针改良
     *
     * @param nums
     * @param target
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.SS)
    public static int[] searchRange4(int[] nums, int target) {
        int[] result = {-1, -1};
        int len = nums.length, left = 0, right = len - 1;
        if (len == 0 || target < nums[0] || target > nums[len - 1]) return result;
        while (left < len) {
            if (nums[left] < target) {
                left++;
            } else {
                if (nums[left] == target) {
                    result[0] = left;
                }
                break;
            }
        }
        while (right >= 0) {
            if (nums[right] > target) {
                right--;
            } else {
                if (nums[right] == target) {
                    result[1] = right;
                }
                break;
            }
        }
        return result;
    }

    /**
     * 二分法优化 TODO
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/java-ji-yi-er-fen-cha-zhao-qing-xi-si-lu-zhi-xing-/
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange5(int[] nums, int target) {
        int[] result = {-1, -1};
        int len = nums.length, left = 0, right = len - 1;
        if (len == 0 || target < nums[0] || target > nums[len - 1]) return result;

        return result;
    }
}
