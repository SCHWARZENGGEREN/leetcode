package com.example.leetcode.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author renxinheng
 * @ClassName ArrayUtil
 * @createTime 2023/8/9
 * @desc
 */
public class ArrayUtil {

    public static void printMultiArray(int[][] array) {
        printMultiArray(array, String::valueOf);
    }

    public static void printMultiArray(int[][] array, Function<Integer, String> mapper) {
        Stream.of(array).forEach(arr -> System.out.println(
                Arrays.stream(ArrayUtils.toObject(arr))
                        .map(mapper)
                        .collect(Collectors.joining(", "))
        ));
    }

    public static void printTripleArray(int[][][] array) {
        Stream.of(array).forEach(arr0 -> {
            Stream.of(arr0).forEach(arr1 -> {
                System.out.println(
                        Arrays.stream(ArrayUtils.toObject(arr1))
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))
                );
            });
        });
    }
}
