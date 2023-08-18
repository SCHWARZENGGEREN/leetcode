package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author renxinheng
 * @ClassName MinimumNumberOfFrogsCroaking
 * @createTime 2023/8/18
 * @desc 1419. 数青蛙
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 * <p>
 * 示例 1：
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙 “呱呱” 两次
 * <p>
 * 示例 2：
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "cr oak    "
 * 第二只青蛙 "  c   roak"
 * <p>
 * 示例 3：
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 * <p>
 * 提示：
 * 1 <= croakOfFrogs.length <= 10^5
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 */
public class MinimumNumberOfFrogsCroaking {

    public static void main(String[] args) {
        System.out.println(minNumberOfFrogs1("croakcroak"));
        System.out.println(minNumberOfFrogs1("crcoakroak"));
        System.out.println(minNumberOfFrogs1("croakcrook"));
        System.out.println(minNumberOfFrogs1("crcoarkcoraokak"));
    }

    public static final String template = "croak";

    /**
     * 把青蛙分为5种,即刚才发出c r o a k 的青蛙,
     * 如果当前发出声音是c, 那么有两种情况:
     *      1,刚才每发出k,是一只新的青蛙; 2,刚才发出k了,是旧的青蛙;
     * 如果当前发出声音不是c,那么说明有青蛙叫声从上一个字母转移到了当前的字母
     * 最后统计所有发出k的青蛙数,如果有不是k的,就说明有问题;
     * 也可以理解为青蛙赛跑,字母理解为圆形赛道,每在一个位置出现一个青蛙,就从上一个位置扣掉一个青蛙;
     *      如果是c位青蛙,则要看k位是否有,有的话就扣除,没有就不管,最后统计所有到达k位的青蛙即可
     *
     * @param croakOfFrogs
     * @return
     */
    @Score(time = Score.S.A,memory = Score.S.SS)//内存使用少,能否空间换时间?
    public static int minNumberOfFrogs1(String croakOfFrogs) {
        int[] croaks = new int['s'];
        for (char c : croakOfFrogs.toCharArray()) {
            //如果是c,前面不一定有k,有的话就-1
            char previousChar = getPrevious(c);
            if (previousChar != 'k' || croaks[previousChar] > 0) {
                croaks[previousChar]--;
            }
            if (croaks[previousChar] < 0) {
                return -1;
            }
            croaks[c]++;
        }
        //如果有没叫到k的
        if (croaks['c'] > 0 || croaks['r'] > 0 || croaks['o'] > 0 || croaks['a'] > 0) {
            return -1;
        }
        return croaks['k'];
    }

    private static char getPrevious(char ch) {
        return ch == 'r' ? 'c' :
               ch == 'o' ? 'r' :
               ch == 'a' ? 'o' :
               ch == 'k' ? 'a' :
                           'k';
    }

    /**
     * 可以理解为最多并行的croak数量?
     * 遍历字符,每出现一个'c',添加一个青蛙并记录呱呱进度0,按顺序每次到'k',移除第一个青蛙;
     * 如果遍历结束有剩余的青蛙,说明不是完整的蛙鸣;那么过程中同时并存最多的青蛙即是答案
     * todo 超时
     *
     * @param croakOfFrogs
     * @return
     */
    @Score(time = Score.S.OT)
    public static int minNumberOfFrogs(String croakOfFrogs) {
        int maxFrogs = 0, frogCount = 0, idx = -1;
        List<Integer> frogs = new ArrayList<>();
        boolean matched;
        for (char ch : croakOfFrogs.toCharArray()) {
            matched = false;
            if ((idx = template.indexOf(ch)) == 0) {
                //new frog
                frogs.add(0);
                frogCount++;
            } else {
                for (int i = 0; i < frogs.size(); i++) {
                    if (frogs.get(i) != null && frogs.get(i) == idx - 1) {
                        //匹配到青蛙
                        frogs.set(i, idx);
                        if (idx == 4) {
                            frogs.set(i, null);
                            frogCount--;
                        }
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    //未匹配到
                    return -1;
                }
            }
            maxFrogs = Math.max(maxFrogs, frogCount);
        }
        if (frogs.stream().anyMatch(Objects::nonNull)) {
            return -1;
        }
        return maxFrogs;
    }

    private static Integer matchFrogs(List<Integer> frogs, char ch, Integer frogCount) {
        int idx = -1;
        if ((idx = template.indexOf(ch)) == 0) {
            //new frog
            frogs.add(0);
            frogCount++;
        } else {
            for (int i = 0; i < frogs.size(); i++) {
                if (frogs.get(i) != null && frogs.get(i) == idx - 1) {
                    //匹配到青蛙
                    frogs.set(i, idx);
                    if (idx == 4) {
                        frogs.set(i, null);
                        frogCount--;
                    }
                    return frogCount;
                }
            }
            //未匹配到
            return -1;
        }
        return frogCount;
    }
}
