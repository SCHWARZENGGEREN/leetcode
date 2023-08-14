package com.example.leetcode.other.spring_bean_cache;

/**
 * @Auther: Rxh
 * @Date: 2021/8/5 14:52
 * @Description:
 */
public class Start {

    public static void main(String[] args) {
        BeanCreateDispatcher dispatcher = new BeanCreateDispatcher();
        dispatcher.create(BeanA.class, BeanB.class);

        System.out.println(dispatcher.getBeanMap());
    }
}
