package com.example.leetcode.solutions;

/**
 * @Auther: Rxh
 * @Date: 2019/9/5 10:37
 * @Description: 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(S2.maxArea(new int[]{2, 3, 5, 1, 4}));
    }

    static class S1 {
        /**
         * 公式:cap = Max((idx2-idx1)*(Min(val2,val1)))
         * 能计算出所有可能,但是效率很低
         *
         * @param height
         * @return
         */
        static int maxArea(int[] height) {
            if (height != null && height.length > 1) {
                int capMax = 0;
                for (int i = 0; i < height.length; i++) {
                    for (int j = i - 1; i != 0 && j >= 0; j--) {
                        int cap = (i - j) * Math.min(height[j], height[i]);
                        capMax = Math.max(capMax, cap);
                    }
                }
                return capMax;
            }
            return 0;
        }
    }

    static class S2 {
        /**
         * 跳过不必要的比较计算
         * 双指针法:移动左右两边的角标,哪边短就移动哪边,直至两个角标相遇
         * TODO 为什么双指针跳过的那些角标是不需要比较的?
         *
         * @param height
         * @return
         */
        static int maxArea(int[] height) {
            if (height != null && height.length > 1) {
                int maxCap = 0, leftIdx = 0, rightIdx = height.length - 1;
                while (leftIdx < rightIdx) {
                    maxCap = Math.max(maxCap, (rightIdx - leftIdx) * Math.min(height[leftIdx], height[rightIdx]));
                    //哪边较短就向对面移动
                    if (height[leftIdx] < height[rightIdx]) {
                        leftIdx++;
                    }else {
                        rightIdx--;
                    }
                }
                return maxCap;
            }
            return 0;
        }
    }

}
