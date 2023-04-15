package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2019/9/3 15:33
 * @Description:
 */
public class Demo {

    /**
     * 缓缓飘落的枫叶像思念
     * 被伤透的心能不能继续爱我
     *
     * @param args
     */
    public static void main(String[] args) {
//        new Book(true);
//        System.gc();

//        System.out.println(getClassName("remove-nth-node-from-end-of-list"));

//        testSystemArrayCopy();
        ArrayList<String> list = new ArrayList<>();
        list.add(":");
        list.remove(-1);
    }

    static void testSystemArrayCopy() {
        int[][] twoDimonArr = {{1, 2, 3}, {4, 5, 6}};
        int[][] dest = new int[2][3];
        System.arraycopy(twoDimonArr, 0, dest, 0, twoDimonArr.length);
        System.out.println(dest);
        Stream.of(dest).forEach(arr -> System.out.print(Arrays.toString(arr)));
        System.out.println();
        twoDimonArr[0][0] = -1;
        Stream.of(dest).forEach(arr -> System.out.print(Arrays.toString(arr)));
    }

    static class Book {
        private boolean flag;

        public Book(boolean flag) {
            this.flag = flag;
        }

        /**
         * 垃圾清理器执行前执行的操作,可以做一些校验
         *
         * @throws Throwable
         */
        @Override
        protected void finalize() throws Throwable {
            if (flag) {
                System.out.println("Error : flag is not clear");
            }
//            super.finalize();
        }
    }

    //remove-nth-node-from-end-of-list
    private static String getClassName(String title) {
        String[] titles = title.split("-");
        StringBuilder result = new StringBuilder();
        for (String string : titles) {
            String firstStr = string.substring(0, 1);
            result.append(string.replaceFirst(firstStr, firstStr.toUpperCase()));
        }
        return result.toString();
    }

    /**
     * 测试享元模式
     */
    public void test1() {
        Integer i = 1;
        Integer i1 = 1;
        Integer i2 = new Integer(1);//创建了对象,没有使用常量池
        System.out.println(i == i1);
        System.out.println(i == i2);
        i = 200;//超出了常量池范围,仍然分配了新对象
        i1 = 200;
        System.out.println(i == i1);
    }

    public void test2() {
        String str1 = "通话";
        String str2 = "重地";
        HashSet<String> set = new HashSet<>();
        set.add(str1);
        set.add(str2);

        HashMap<Object, Object> map = new HashMap<>();
        map.put(null, "1");
        map.put(str1, 1);
        map.put(str2, 2);
        System.out.println(map);
    }

    public void test3() {
        Thread thread = new Thread();
        thread.start();
        thread.run();

    }
}
