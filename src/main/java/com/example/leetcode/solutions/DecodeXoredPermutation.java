package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2021/5/11 13:41
 * @Description: 1734. 解码异或后的排列
 * 给你一个整数数组 perm ，它是(前 n 个正整数)的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
 * 比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * <p>
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * <p>
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 * <p>
 * 提示：
 * <p>
 * 3 <= n < 10^5
 * n 是奇数。
 * encoded.length == n - 1
 */
public class DecodeXoredPermutation {

    public static void main(String[] args) {
        int[] encoded = {2, 1};
        System.out.println(Arrays.toString(decode(encoded)));
    }

    /**
     * 看题4小时,做题10分钟,题意太容易迷惑
     * pe是奇数个数字,且数字是1-n不重复,en长度是偶数,假设en长度为2n,那么可以得到:
     * pe[1~2n+1]^ -> peTot
     * en[odd]^ = en[1~2n-1]^ = pe[1]^pe[2] ^pe[3]^pe[4] ^... ^pe[2n-1]^pe[2n] = pe[1~2n]^ -> enOdd
     * 那么:
     * peTot^enOdd = pe[0]
     * 根据之前的思路:
     * pe[1] = pe[0]^en[0],以此类推
     *
     * @param encoded
     * @return
     * @see DecodeXoredArray#decode(int[], int)
     */
    @Score(time = Score.S.SS, memory = Score.S.D)
    public static int[] decode(int[] encoded) {
        //先算perm累积
        int permLen = encoded.length + 1, permTotal = 0;
        int[] perm = new int[permLen];
        while (permLen > 0)
            permTotal ^= permLen--;
        //再算encoded偶数下标累积,结果为perm
        int encodedOddIdxTot = 0, encodedIdx = encoded.length - 1;
        while (encodedIdx >= 0) {
            encodedOddIdxTot ^= encoded[encodedIdx];
            encodedIdx -= 2;
        }
        //获取perm初始值
        perm[0] = encodedOddIdxTot ^ permTotal;
        int permIdx = 0;
        while (permIdx < encoded.length) {
            perm[permIdx + 1] = perm[permIdx] ^ encoded[permIdx];
            permIdx++;
        }

        return perm;
    }
}
