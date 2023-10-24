package com.example.leetcode.other.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author renxinheng
 * @ClassName FOR_Compression
 * @createTime 2023/10/16
 * @desc 倒排索引中,每个索引需要记录文本id列表,
 * 为了节省空间使用编码技术，Frame Of Reference（FOR）
 * Step 1：Delta-encode —— 增量编码
 * Step 2：Split into blocks —— 分割成块
 * Step 3：Bit packing —— 按需分配空间
 *
 * <image>/src/main/resources/images/FOR编码.png</image>
 */
public class FOR_Compression {

    public static void main(String[] args) {
        int[] idList = new int[]{73, 300, 302, 332, 343, 372};
        int maxBit = getMemBit(idList[idList.length-1]);
        List<MemoryUnit<Integer>> basicData = IntStream.of(idList).boxed()
                .map(i -> new MemoryUnit<>(maxBit, i)).collect(Collectors.toList());
        System.out.println("压缩前空间(BIts): "+ calcMemory(basicData));
        System.out.println("压缩前数据: "+ basicData);

        basicData = compressedCoding(basicData);
        System.out.println("压缩后前空间(BIts): "+ calcMemory(basicData));
        System.out.println("压缩后数据: "+ basicData);
    }

    /**
     * FOR编码,压缩节省内存
     * 1,以元素增量代替元素值
     * 2,对数据分组并分配空间
     * @return
     */
    public static List<MemoryUnit<Integer>> compressedCoding(List<MemoryUnit<Integer>> basicData) {
        //1 以增量代替
        List<Integer> incrementList = new ArrayList<>();
        incrementList.add(basicData.get(0).getValue());
        for (int i = 1; i < basicData.size(); i++) {
            incrementList.add(basicData.get(i).getValue() - basicData.get(i - 1).getValue());
        }
        List<MemoryUnit<Integer>> compressData = incrementList.stream().map(i -> new MemoryUnit<Integer>(0, i)).collect(Collectors.toList());
        //分组 先按3个一组吧
        int maxValue = 0;
        int group = 1;
        for (int i = 0; i < compressData.size(); i++, group++) {
            maxValue = Math.max(maxValue, compressData.get(i).getValue());
            if (group == 3 || i == compressData.size() - 1) {
                //计算字节数
                int memBit = getMemBit(maxValue);
                while (group-- > 0) {
                    compressData.get(i - group).set_byte(memBit);
                }
                group = 1;
                maxValue = 0;
            }
        }
        return compressData;
    }

    private static int calcMemory(List<MemoryUnit<Integer>> memoryUnits){
        return  memoryUnits.stream().map(MemoryUnit::get_byte)
                .mapToInt(Integer::intValue).sum();
    }

    /**
     * 计算一个数据占据的空间大小
     * 1, 基本类型:
     *  boolean、byte 占用 1 byte，
     *  char、short 占用 2 bytes，
     *  int、float 占用 4 bytes，
     *  long、double 占用 8 bytes
     * 2,对象,包含4部分
     *  2.1,对象头,占用很少的字节，表述Object当前状态的信息;
     *      在当前的JVM版本中（Hotspot），“对象头”占用的字节数如下：
     *      一个普通对象，占用8 bytes
     *      数组，占用 12 bytes，包含普通对象的 8 bytes + 4 bytes（数组长度）
     *  2.2, 基本类型域占用的空间
     *  2.3, 引用类型域占用的空间(引用类型域指 其他对象的引用，每个引用占用4个字节)
     *  2.4, 填充物占用的空间,
     *      填充物:由于对象所占用的数据大小为8的整数倍，有时前三者占用空间大小总和不是8整数倍，需要填充几个字节使其为8的整数倍
     *      这里为了方便计算,使用bit吧
     * @param i
     * @return
     */
    private static int getMemBit(int i){
        int bit = 0;
        while (i > 0){
            i >>= 1;
            bit++;
        }
        return bit;
    }

    private static void littleLoop(){
        for (int i = 0, group = 1; i < 100; i++, group++) {
            System.out.println("i: " + i + "group: " + group);
            if (group == 3){
                group = 0;
            }
        }
    }

    /**
     * 模拟内存单位
     *
     */
    @Data
    @AllArgsConstructor
    public static class MemoryUnit<T>{
        private Integer _byte;//空间
        private T value;
    }
}
