package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Tips;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther: Rxh
 * @Date: 2019/9/19 13:30
 * @Description: 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ,
 * 使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 */
public class _4Sum {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{
                -2, -1, -1, 1, 1, 2, 2
        }, 0));
//        System.out.println(fourSum(new int[]{
//                82597, -9243, 62390, 83030, -97960, -26521, -61011, 83390, -38677, 12333,
//                75987, 46091, 83794, 19355, -71037, -6242, -28801, 324, 1202, -90885, -2989,
//                -95597, -34333, 35528, 5680, 89093, -90606, 50360, -29393, -27012, 53313,
//                65213, 99818, -82405, -41661, -3333, -51952, 72135, -1523, 26377, 74685,
//                96992, 92263, 15929, 5467, -99555, -43348, -41689, -60383, -3990, 32165,
//                65265, -72973, -58372, 12741, -48568, -46596, 72419, -1859, 34153, 62937,
//                81310, -61823, -96770, -54944, 8845, -91184, 24208, -29078, 31495, 65258, -99818, -12333
//        }, 0));

    }

    /**
     * 双层循环内嵌双指针:
     * 先排序,在最内层循环里,使用双指针,dest片大时right左移;偏小是left右移
     * <p>
     * 仍使用hashcode去重,根据源码知道:AbstractList中的hash算法是根据每个元素累计计算得来的,因此不会重复??
     *
     *
     * @param nums
     * @param target
     * @return
     */
    @Tips(tip = "遍历比较复杂时可以考虑适当剪枝去重")
    @Score(time = Score.S.A,memory = Score.S.C)
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> hashs = new ArrayList<>();
        int dest;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//去重
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;//剪枝
            for (int j = i + 1; j != i && j < nums.length - 2; j++) {
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;//剪枝
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;//去重
                int k = j + 1, l = nums.length - 1;
                while (k < l) {
                    //去重
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        k++;
                    }
                    if (l < nums.length - 1 && nums[l] == nums[l + 1]) {
                        l--;
                    }
                    if (k >= l) break;

                    //对比
                    dest = nums[i] + nums[j] + nums[k] + nums[l];
                    if (dest == target) {
                        List<Integer> res = IntStream.of(nums[i], nums[j], nums[k], nums[l])
                                .boxed()
                                .collect(Collectors.toList());
                        int hashCode = res.hashCode();
                        if (!hashs.contains(hashCode)) {
                            hashs.add(hashCode);
                            results.add(res);
                        }
//                        System.out.println(res);

                        k++;
                        l--;
                    } else if (dest > target) {
                        l--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return results;
    }
}
