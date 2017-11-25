package com._3.iter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static List<Character> list;
    private static final char DEL_CHAR = 'b';

    @Before
    public void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add((char) ('a' + i));
        }
    }

    @After
    public void destory() {

    }

    /**
     * iter能表示当前所在的位置, 删除元素后集合结构不发生变化, 不会发生异常
     */
    @Test
    public void iterRemoveIterTravel() {
        Iterator<Character> iterator = list.iterator();
        while (iterator.hasNext()) {
            char character = iterator.next();
            System.out.println("char=" + character);

            if (DEL_CHAR == character) {
                iterator.remove();
                System.out.println("remove " + DEL_CHAR);
            }
        }

        System.out.println("list=" + list);
    }

    /**
     * 没有出现错误, 但是隐含一个bug, 少输出了被删除元素的后面元素
     */
    @Test
    public void collectionRemoveIndexTravel() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("char=" + list.get(i));
            if (list.get(i) == DEL_CHAR) {
                System.out.println("remove " + DEL_CHAR);
                list.remove(i);
            }
        }
        System.out.println("list=" + list);
    }

    /**
     * 并发修改异常出现在Iterator和Collection不匹配遍历中
     */
    @Test
    public void collectionRemoveIterTravel() {
        Iterator<Character> iterator = list.iterator();

        while (iterator.hasNext()) {
            Character character = iterator.next();
            System.out.println("char=" + character);

            if (character == DEL_CHAR) {
                System.out.println("remove " + character);
                list.remove(character);
            }

        }

        System.out.println("list=" + list);
    }

    /**
     * foreach就是Iterator的封装, 也会抛出并发修改异常
     */
    @Test
    public void collectionRemoveForeachTravel() {

        for (Character charactor : list) {
            System.out.println("char=" + charactor);
            if (charactor == DEL_CHAR) {
                System.out.println("remove " + charactor);
                list.remove(charactor);
            }
        }

        System.out.println("list=" + list);

    }

}
