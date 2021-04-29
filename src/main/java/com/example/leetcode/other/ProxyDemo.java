package com.example.leetcode.other;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @Auther: Rxh
 * @Date: 2020/10/10 14:49
 * @Description: 动态代理
 */
public class ProxyDemo {

    /**
     * 代理:在不改变目标对象的前提下对其进行改变/增强
     * 静态代理:为每一个目标类都提供一个代理类
     * 动态代理:使用工具类动态为目标类代理
     * 动态代理有两种:
     * 1,jdk动态代理,代理的目标类必须实现了接口,实现原理是反射
     * 2,cglib动态代理,不需要对象实现接口,实现原理是动态为目标生成增强子类
     */

    public static void main(String[] args) {
        Target target = new Target();
        target.doSth();
        new StaticProxy(target).doSth();

        Class<? extends Target> targetClass = target.getClass();
        //基于jdk的动态代理
        AbstractTarget jdkProxyTarget = (AbstractTarget) Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                targetClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JdkProxy  do enhance...");
                        return method.invoke(target);
                    }
                }
        );
        jdkProxyTarget.doSth();

        //基于cglib的动态代理
        Target cglibProxyTarget = (Target) Enhancer.create(
                targetClass,
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("cglib do enhance...");
                        return method.invoke(target);
                    }
                }
        );
        cglibProxyTarget.doSth();
    }


    interface AbstractTarget {
        void doSth();
    }

    static class Target implements AbstractTarget {

        @Override
        public void doSth() {
            System.out.println("target method invoke...");
        }
    }

    static class StaticProxy implements AbstractTarget {
        private AbstractTarget target;

        StaticProxy(AbstractTarget target) {
            this.target = Objects.requireNonNull(target);
        }

        @Override
        public void doSth() {
            enhance();
            target.doSth();
        }

        private void enhance() {
            System.out.println("do enhance...");
        }
    }
}
