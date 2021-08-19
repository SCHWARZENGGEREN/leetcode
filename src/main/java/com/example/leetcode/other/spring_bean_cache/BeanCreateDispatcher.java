package com.example.leetcode.other.spring_bean_cache;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2021/8/5 14:52
 * @Description: 模拟spring 用三级缓存 解决单例模式下循环依赖问题
 */
public class BeanCreateDispatcher {

    private static final Map<Class, Object> beanMap = new HashMap<>();//成品
    private static final Map<Class, Object> earlyBeanMap = new HashMap<>();//半成品
    private static final Set<Class> waitingBeans = new HashSet<>();


    public void create(Class<?>... classes) {
        for (Class<?> aClass : classes) {
            //构建bean
            try {
                createBean(aClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private <T> T createBean(Class<T> aClass) throws Exception {
        Object instance = beanMap.get(aClass);
        String typeName = aClass.getSimpleName();
        if (Objects.isNull(instance)) {
            instance = earlyBeanMap.get(aClass);
            if (Objects.isNull(instance)) {
                instance = aClass.newInstance();
                System.out.println("Bean: [" + typeName + "] 初始化,并放入半成品Map");
                earlyBeanMap.put(aClass, instance);

            }
            if (waitingBeans.contains(aClass)) {
                System.out.println("于半成品Map中发现Bean: [" + typeName + "] ,并返回");
                return (T) instance;
            }
            settingFields(aClass, instance);

            System.out.println("Bean: [" + typeName + "] 创建完成,并放入成品Map");
            earlyBeanMap.remove(aClass);
            beanMap.put(aClass, instance);
        }
        return (T) instance;
    }

    private void settingFields(Class c, Object ins) throws Exception {
        System.out.println("为Bean: [" + c.getSimpleName() + "] 进行属性赋值");
        waitingBeans.add(c);
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("对Bean: " + c.getSimpleName() + " 的属性: " + field.getType().getSimpleName() + "初始化--");
            Object bean = createBean(field.getType());
            assert bean != null;

            field.setAccessible(true);
            field.set(ins, bean);
        }
        waitingBeans.remove(c);
    }

    public Map<Class, Object> getBeanMap() {
        return beanMap;
    }


}
