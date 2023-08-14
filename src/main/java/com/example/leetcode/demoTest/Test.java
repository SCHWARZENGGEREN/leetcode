package com.example.leetcode.demoTest;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author renxinheng
 * @ClassName Test
 * @createTime 2023/5/9
 * @desc
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Service service = new Service(latch);
        Runnable task = service::exec;
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("main thread await. ");
        long l = System.currentTimeMillis();
        latch.await(20, TimeUnit.SECONDS);

        System.out.println("time cost: "+ (System.currentTimeMillis() - l));
        System.out.println("waiting thread: "+ latch.getCount());
    }
}

class Service {
    private CountDownLatch latch;

    public Service(CountDownLatch latch) {
        this.latch = latch;
    }

    public void exec() {
        try {
            System.out.println(Thread.currentThread().getName() + " execute task. ");
            sleep(new Random(System.currentTimeMillis()).nextInt(20));
            System.out.println(Thread.currentThread().getName() + " finished task. ");
        } finally {
            latch.countDown();
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
