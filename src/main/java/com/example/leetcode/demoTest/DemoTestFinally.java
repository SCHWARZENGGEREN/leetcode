package com.example.leetcode.demoTest;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Auther: Rxh
 * @Date: 2019/9/26 10:42
 * @Description:
 */
public class DemoTestFinally {

    public static void main(String[] args) {
        testArrayListRemove();
    }

    private int i = 0 ;

    @Test
    public void test(){
        System.out.println(i);
        testFinally();
        System.out.println(i);
    }
    private boolean testFinally(){
        try {
            int j = this.i / 0;
        }catch (Exception e){

        }finally {
            i = 2;
        }
        return true;
    }

    /**
     * ArrayList 中的 remove方法,修改了modeCount,而这个操作发生在rangeCheck和elementData[idx]之间(这两个方法配合能检测角标是否越界,是一种耦合),
     * 猜测在循环中调用remove方法,并传入负数,会先出现 角标越界异常
     * 将idx改为正常后,则会出现 并发修改异常
     *
     * 分析:在调用add方法时,ArrayLIst的modCount被修改;
     * 使用for循环时,调用了ArrayList的iterator()方法,生成了一个ArrayList.Itr,其expectedModCount初始化为arrayList的modCount
     * 在使用Itr以外的方法修改ArrayList时,modCount被修改,而expectedModCount没被修改导致二者不一致;
     * 在调用Itr的一些方法(其实是Itr实现的所有方法)是会触发校验而产生并发修改异常
     */
    private static void testArrayListRemove(){
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        for (String s :list) {
            list.remove(0);
        }
    }

    static void testBreakAndFinally() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(i+" ===");
                if (i >= 8) break;
            } catch (Exception e) {

            } finally {
                System.out.println("finally:" + i);
            }
        }
    }
}
