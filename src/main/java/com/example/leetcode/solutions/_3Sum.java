package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;
import com.example.leetcode.other.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/9/6 13:30
 * @Description: 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class _3Sum {

    public static void main(String[] args) {
        System.out.println(threeSum1(new int[]{
                82597, -9243, 62390, 83030, -97960, -26521, -61011, 83390, -38677, 12333,
                75987, 46091, 83794, 19355, -71037, -6242, -28801, 324, 1202, -90885, -2989,
                -95597, -34333, 35528, 5680, 89093, -90606, 50360, -29393, -27012, 53313,
                65213, 99818, -82405, -41661, -3333, -51952, 72135, -1523, 26377, 74685,
                96992, 92263, 15929, 5467, -99555, -43348, -41689, -60383, -3990, 32165,
                65265, -72973, -58372, 12741, -48568, -46596, 72419, -1859, 34153, 62937,
                81310, -61823, -96770, -54944, 8845, -91184, 24208, -29078, 31495, 65258
        }));
    }

    /**
     * 笨方法:一一对比
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    static List<List<Integer>> threeSum0(int[] nums) {
        if (nums != null && nums.length >= 3) {
            List<List<Integer>> results = new ArrayList<>();

            int length = nums.length;
            for (int i = 0; i < length; i++) {
                int iVal = nums[i];

                for (int j = i + 1; j < length; j++) {
                    int jVal = nums[j];
                    int sum = iVal + jVal;
                    for (int k = j + 1; k < length; k++) {
                        int kVal = nums[k];
                        if ((sum >= 0 && kVal <= 0) || (sum < 0 && kVal > 0)) {
                            if (iVal + jVal + kVal == 0) {
                                List<Integer> result = new ArrayList<>();
                                result.add(iVal);
                                result.add(jVal);
                                result.add(kVal);
                                results.add(result);
                            }
                        }
                    }
                }
            }
            return results;
        }
        return null;
    }

    /**
     * 双指针法:先将数组排序,然后遍历数组,将每个元素作为destNum,
     * 然后设置两个指针,分别放在destNum后面的起止点,然后进行计算,如果sum+destNum>0,则右边指针向左移动;
     * 反之则左边指针向右移动;如果等于0,则收集这一组数字,然后继续移动指针
     * <p>
     * TODO 使用了自定义的快速排序后测试不通过,但对比自定义快排和Arrays.sort发现排序结果一致,WHY?? 而且这个方案耗时太多
     * TODO 哈希碰撞问题
     * @param nums
     * @return
     */
    @Unsettled
    @Score(time = Score.S.SS, memory = Score.S.SS)
    static List<List<Integer>> threeSum1(int[] nums) {
        int length = nums.length;
        List<List<Integer>> results = new ArrayList<>();
        if (length >= 3) {
            List<Integer> hashCodes = new ArrayList<>();
            Arrays.sort(nums);
//                sort(nums, 0, length - 1);//使用快速排序虽然结果一样,但效率相当不稳定,只有在部分情况下比Arrays.sort效率高
            if (nums[0] > 0 || nums[length - 1] < 0) return results;//仅仅加了这个校验,效率提升一倍??
            for (int i = 0; i < length - 2; i++) {
                //如果iVal>0,则其后的数字一定大于零
                int iVal = nums[i];
                if (iVal > 0) break;
                if (i != 0 && iVal == nums[i - 1]) continue;
                int j = i + 1, k = length - 1;
                while (j < k) {
                    int jVal = nums[j];
                    int kVal = nums[k];
                    if (j > i + 1 && jVal == nums[j - 1]) {
                        j++;
                        continue;
                    }
                    if (k < length - 1 && kVal == nums[k + 1]) {
                        k--;
                        continue;
                    }
                    if (jVal + kVal + iVal == 0) {
                        List<Integer> result = new ArrayList<>();
                        result.add(iVal);
                        result.add(jVal);
                        result.add(kVal);
                        int hashCode = result.hashCode();
                        if (!hashCodes.contains(hashCode)) {
                            results.add(result);
                            hashCodes.add(hashCode);
                        }
                        j++;
                        k--;
                    } else if (jVal + kVal + iVal > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return results;
    }

    /**
     * 选择排序 - 首先初始化最小元素索引值为首元素，依次遍历待排序数列，
     * 若遇到小于该最小索引位置处的元素则刷新最小索引为该较小元素的位置，
     * 直至遇到尾元素，结束一次遍历，并将最小索引处元素与首元素交换；
     * 然后，初始化最小索引值为第二个待排序数列元素位置，同样的操作，可得到数列第二个元素即为次小元素；以此类推。
     *
     * @param nums
     */
    public static void sort(int[] nums, int start, int end) {
        QuickSort.sort(nums, start, end);
    }
}
