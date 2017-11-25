package com._6.priority.queue;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * 输入N个元素及一个整数K, 这N个元素的集可以是全序集(元素可以排序), 找出第K个最大的元素
 */
public class TopK {
    private static final String FILE_NAME = "TopKElements.txt";
    private static final int PROBLEM_SIZE = 100000;

    private int[] elements;
    private int k = 100;

    /**
     * 读入输入量
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        Random random = new Random();

        if (!new File(FILE_NAME).exists()) {
            new File(FILE_NAME).createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

        String intStrs = reader.readLine();

        if (StringUtils.isBlank(intStrs)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (int i = 0; i < PROBLEM_SIZE; i++) {
                writer.write(random.nextInt(PROBLEM_SIZE) + ",");
            }
            writer.flush();
            writer.close();

            reader = new BufferedReader(new FileReader(FILE_NAME));
            intStrs = reader.readLine();
        }

        String[] ss = intStrs.split(",");

        elements = new int[ss.length];

        for (int i = 0; i < ss.length; i++) {
            elements[i] = Integer.parseInt(ss[i]);
        }

        System.out.println("N=" + elements.length + ", K=" + k);
    }

    /**
     * 输出正确结果
     */
    @After
    public void destory() {
        Arrays.sort(elements);
        System.out.println("正确答案: 第" + k + "大的元素是: " + elements[elements.length - k]);
    }

    /**
     * 实际运算
     */
    @Test
    public void doFind() {

        // 最小堆解决最大topK
        ArrayList<Integer> list = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            list.add(elements[i]);
        }
        // 使用heapify
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(list);

        for (int i = k; i < elements.length; i++) {
            Integer min = minHeap.peek();
            if (elements[i] > min) {
                minHeap.remove();
                minHeap.add(elements[i]);
            }
        }

        System.out.println("得到:" + minHeap.peek());
        System.out.println("堆元素(前" + k + "大元素):" + Arrays.toString(minHeap.toArray()));
    }

}
