package com._7.sort;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * jdk中的泛型排序就是基于归并排序的
 * 因为比较成本高, 挪动成本低(引用挪动)
 * <p>
 * 而基本类型是快速排序
 */
public class MergeSort {
    @Test
    public void testLinkedListSort() {
        LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }

        Collections.sort(list);

        list.forEach(element -> System.out.print(element + ", "));
    }
}
