package com.interview.zulong;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Main {
    private CircularQueue<String> strPool;

    @Before
    public void init() {
        strPool = new CircularQueue<>();
    }

    @After
    public void destroy() {
    }

    @Test
    public void testAdd_justFull() {
        for (int i = 1; i <= 100; i++) {
            strPool.add("第 `" + i + "` 个字符串");
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }

    @Test
    public void testAdd_overflow() {
        testAdd_justFull();

        for (int i = 101; i <= 105; i++) {
            strPool.add("第 `" + i + "` 个字符串");
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }

    @Test
    public void testAdd_overflowVeryMuch() {

        for (int i = 1; i <= 205; i++) {
            strPool.add("第 `" + i + "` 个字符串");
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }

    @Test
    public void testRemove() {
        testAdd_overflow();

        for (int i = 0; i < 10; i++) {
            System.out.println(strPool.removeTail());
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }

    @Test
    public void testRemove_clear() {
        testAdd_overflowVeryMuch();

        for (int i = 0; i < 100; i++) {
            System.out.println(strPool.removeTail());
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }

    @Test
    public void reuse() {
        testRemove_clear();

        for (int i = 1; i <= 100; i++) {
            strPool.add("重入字符串" + i);
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());
    }


    @Test
    public void testTimer() {
        for (int i = 1; i <= 100; i++) {
            strPool.add("第 `" + i + "` 个字符串");
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束! size=" + strPool.size());

        // 当前线程等3秒
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " 运行结束10秒后! size=" + strPool.size());
    }

}
