package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/9/10 13:30
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @imagePath /rwt.png
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height1 = {4, 10, 7, 5, 8, 6, 3};
//        System.out.println("S1: " + trap1(height));
//        System.out.println("S2: " + trap2(height));
//        System.out.println("S3: " + trap3(height1));
        System.out.println("S4: " + trap4(height1));
    }

    /**
     * 按照行求和
     * 从y轴从下往上遍历,分别求每一层符合条件的x角标位置,并累计
     * <p>
     * <p>
     * 时间复杂度：如果最大的数是 mm，个数是 nn，那么就是 O(m*n)O(m∗n)。
     * 空间复杂度：O(1)O(1)。
     *
     * @param height
     * @return
     * @from leetcode
     */
    @Score(time = Score.S.OT, memory = Score.S.NONE)
    static int trap1(int[] height) {
        int vol = 0, len = height.length;
        if (len >= 3) {
            int yHei = 0;
            //y轴遍历,遍历对比,直至数组中没有比当前高度高的位置为止
            boolean anyHighter;
            do {
                int i = 0, tmp = 0;
                boolean start = false;
                anyHighter = false;
                while (i <= len - 1) {
                    //如果当前高度大于等于y轴当前高度,则标记为开始坑洼,计算tmp,并重置tmp值;
                    //如果当前高度小于y轴当前高度,并且有标记,则开始累积tmp值
                    int iHei = height[i];
                    if (iHei > yHei) {
                        anyHighter = true;//标记数组中还有比当前y高的pos
                        vol += tmp;
                        tmp = 0;
                        start = true;
                    } else {
                        if (start) {
                            tmp++;
                        }
                    }
                    i++;
                }
                yHei++;
            } while (anyHighter);
        }
        return vol;
    }

    /**
     * 按照列求,把每一列单独列出来,分别向左右遍历求出最高点,然后取出两个最高点之间矮的那个进行对比:
     * 如果较矮的高度高于当前列,则当前列存水量为较矮的减去当前列;如果较矮的高度小于或者等于当前列,则不会有存水
     *
     * @param height
     * @return
     */
    @Score(time = Score.S.C, memory = Score.S.A)
    static int trap2(int[] height) {
        int volum = 0, len = height.length, tmp;
        if (len >= 3) {
            for (int i = 1; i < len - 1; i++) {
                int current = height[i];
                int leftHighest = 0, rightHighest = 0, highest;
                tmp = i;
                while (--tmp >= 0) {
                    leftHighest = Math.max(leftHighest, height[tmp]);
                }
                tmp = i;
                while (++tmp <= len - 1) {
                    rightHighest = Math.max(rightHighest, height[tmp]);
                }
                highest = Math.min(leftHighest, rightHighest);
                if (highest > current) {
                    volum += highest - current;
                }
            }
        }
        return volum;
    }

    /**
     * 对S2的优化:在S2中每遍历一个点都要找出它左右两边的最高点,如果能复用找到的左右侧最高点,就能提高效率
     * 遍历数组分别求出其左右侧最值并保存在两个数组中,与S2不同之处在于:在寻找最值过程中真正的遍历只需要两次,其他值对比最值和上一个点的值即可
     * <p>
     * 遍历数组,分别求出每个位置元素最左侧最大值和最右侧最大值,并放在两个数组中,然后用S2中的方式进行按列计算
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param height
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.A)
    static int trap3(int[] height) {
        int len = height.length, volume = 0;
        if (len >= 3) {
            int[] maxLeft = new int[len], maxRight = new int[len];
            for (int i = 1; i <= len - 1; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
            }
            for (int i = len - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
            }
            //初始完maxLeft和maxRight后,分别计算每一列存水量
            int lowerHeight;
            for (int i = 1; i < len - 1; i++) {
                int current = height[i];
                lowerHeight = Math.min(maxLeft[i], maxRight[i]);

                int diff = lowerHeight - current;
                if (diff > 0) volume += diff;
            }
        }
        return volume;
    }

    /**
     * 双指针法:
     * 对S3还能进一步优化,因为我们虽然得到了maxLefts和maxRights,
     * 但它们都只使用了一次,把它们存放在数组里面显然没有必要,我们能不能在遍历每一个点的过程中得到它们?
     * 我们可以在遍历过程中初始化两个指针,代表左侧和右侧
     * <p>
     * 理解:遍历数组,并加入左右两个指针,每次遍历要么left++,要么right--,那么它们最终会相遇,
     * 这样虽然没用到i,但实际以left和right的角度把数组遍历了一遍
     * 分别用两个变量maxleft保存left指针左侧最大值;maxright保存right指针右侧最大值;
     * 那么:如果
     * <p>
     * 遍历并对比max_left和max_right,如果max_left<max_right,
     * 那么就将left角标右移,在遇到比max_right高的点之前计算高度都以max_left为准;
     * 如果遇到的点高度大于max_right,则开始移动right角标,如此反复
     * <p>
     * 以双指针left,right遍历数组,并用两个变量动态更新maxL和maxR
     * 如果height[left-1]<height[right+1],则开始计算left侧,并且此时min(max_l,max_r)等于max_l
     * 如果无法理解,比如hei[left-2]>max_r这种情况,就逆推上一步,如果上一步是这种是这种情况那么指针一定在右侧,
     * TODO
     * <p>
     * <p>
     * 时间复杂度： O(n)。
     * 空间复杂度： O(1)。
     *
     * @param height
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    static int trap4(int[] height) {
        int volume = 0, len = height.length;
        if (len >= 3) {
            int left = 1, right = len - 2, maxLeft = 0, maxRight = 0, diff;
            for (int i = 1; i < len - 1; i++) {
                if (height[left - 1] < height[right + 1]) {
                    maxLeft = Math.max(height[left - 1], maxLeft);
                    diff = maxLeft - height[left];
                    left++;
                } else {
                    maxRight = Math.max(maxRight, height[right + 1]);
                    diff = maxRight - height[right];
                    right--;
                }
                if (diff > 0) volume += diff;
            }
        }
        return volume;
    }

    /**
     * @param height
     * @return
     */
    static int trap5(int[] height) {
        return 0;
    }
}
