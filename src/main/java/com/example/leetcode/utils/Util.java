package com.example.leetcode.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

/**
 * @Auther: Rxh
 * @Date: 2019/11/8 15:41
 * @Description:
 */
public class Util {

    public static void clearConsole() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
        }
    }

    public static <T> void invokeThenExit(Consumer<T> consumer) {
        if (consumer != null) {
            consumer.accept(null);
        }
        System.exit(0);
    }

    public static <T> void printStack(Stack<T> stack) {
        System.out.println(getStackToStringReverse(stack));
    }

    public static <T> String getStackToStringReverse(Stack<T> stack) {
        String string = "";
        if (Objects.nonNull(stack)) {
            string = stack.toString();
            if (!stack.empty()) {
                StringBuilder builder = new StringBuilder(string)
                        .reverse()
                        .replace(0, 1, "[")
                        .replace(string.length() - 1, string.length(), "]");
                string = builder.toString();
            }
        }
        return string;
    }

    public static void printThenExit(String message) {
        System.out.println(message);
        System.exit(0);
    }

    public static void closeCloseable(Closeable closeable) {
        if (Objects.nonNull(closeable)) {
            try {
                closeable.close();
            } catch (IOException e) {
                System.out.println("释放资源失败!");
                e.printStackTrace();
            }
        }
    }

    public static String MD5(String md5) {
        try {
            MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 加密失败");
        }
        return null;
    }

    public static void popUpDir(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void printlnCollection(Collection<T> collection) {
        if (CollectionUtils.isNotEmpty(collection)) {
            collection.forEach(System.out::println);
        }
    }

    public static String getUserChooseDir() {
        String filePath = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("选择的目录: " + filePath);
        }
        return filePath;
    }

    public static void printColumnChart(int[] arr) {
        if (ArrayUtils.isNotEmpty(arr)) {
            int higest = 0;
            for (int high : arr) {
                higest = Math.max(higest, high);
            }
            for (int i = higest; i > 0; i--) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] >= i) {
                        System.out.print("H ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static <T> void judgeInvokeTime(Consumer<T> function) {
        long start = System.currentTimeMillis();
        function.accept(null);
        System.out.println("InvokeTime: " + (System.currentTimeMillis() - start));
    }

    public static <T, R> R executeIf(boolean condition, Function<T, R> function, T param) {
        return condition ? function.apply(param) : null;
    }

    /**
     * 换行打印
     *
     * @param list
     * @return
     */
    public static String printFeedLine(List<String> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder sb = new StringBuilder(list.toString());

            sb.insert(1, "\r\n");
            if (!list.get(list.size()-1).endsWith("\r\n")){
                sb.insert(sb.length() - 2, "\r\n");
            }
            return sb.toString()
                    .replaceAll("\r\n, ",", \r\n");
        }
        return null;
    }


    public static void main(String[] args) {
//        System.out.println(MD5("123456"));
        int[] height = {4, 10, 7, 5, 8, 6, 3};
        printColumnChart(height);

        int[][][] triple = {
                {
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                },
                {
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                },
                {
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                        {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                }
        };
//        printTripleArray(triple);
    }

    public static <T> void printMultiArray(T[][] array) {
        Stream.of(array).forEach(arr -> System.out.println(
                Stream.of(arr)
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
        ));
    }

    public static <T> void printTripleArray(T[][][] array) {
        Stream.of(array).forEach(arr0 -> {
            Stream.of(arr0).forEach(arr1 -> {
                System.out.println(
                        Stream.of(arr1)
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))
                );
            });
        });
    }

    public static <T, R> void calcInvokeTime(Function<T, R> function) {
        long start = System.currentTimeMillis();
        R result = function.apply(null);

        System.out.println("执行结果: "+ result);
        System.out.println("执行耗时: " + (System.currentTimeMillis() - start));
    }
}