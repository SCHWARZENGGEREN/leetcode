package com.example.leetcode.demoTest;

/**
 * @author renxinheng
 * @ClassName TestMultiThread
 * @createTime 2023/5/16
 * @desc
 */
public class TestMultiThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Task(1, 2, () -> System.out.print('A')));
        Thread t2 = new Thread(new Task(2, 3, () -> System.out.print('B')));
        Thread t3 = new Thread(new Task(3, 1, () -> System.out.print('C')));

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();
    }
    /**
     *  t1 a b -> a b
     *  t2 b c -> b c
     *  t3 c a -> wait
     */
    static class Task implements Runnable {
        public Task(Object lock1, Object lock2, Runnable innerTask) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.innerTask = innerTask;
        }

        private final Object lock1;
        private final Object lock2;
        private Runnable innerTask;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock1) {
                    synchronized (lock2) {
                        innerTask.run();
                        //结束后 唤醒下一个
                        lock2.notify();
                    }
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
