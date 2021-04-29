package com.example.leetcode.other;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2020/10/19 16:26
 * @Description: hashmap多线程不安全问题验证
 */
public class TestHashMapMultiThreadUnSafe {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        HashMap<Integer, Object> map = new HashMap<>();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    map.put(i, 1);
                }
                System.out.println("t1: " + map.values().stream().distinct().collect(Collectors.toList()));
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                int j = 0;
                for (int i = 0; i < 100000; i++) {
                    map.put(j--, 2);
                }
                System.out.println("t2: " + map.values().stream().distinct().collect(Collectors.toList()));
            }
        }.start();
    }

    private static void test1() {
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }


    public static class HashMapThread extends Thread {
        private static AtomicInteger ai = new AtomicInteger();
        private static Map<Integer, Integer> map = new HashMap<>();

        @Override
        public void run() {
            while (ai.get() < 1000000) {
                map.put(ai.get(), ai.get());
                ai.incrementAndGet();
            }
        }
    }
}
