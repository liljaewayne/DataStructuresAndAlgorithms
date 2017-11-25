package com.interview.zulong;

import javafx.util.Pair;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 实现一个缓冲池, 要求如下:
 * <p>
 * 1. 每个加入到缓冲池中的对象的生存期为30分钟, 过期要被清除
 * 2. 缓冲池最多存储10000个对象
 * 3. 当缓冲池存储的对象超过10000个时, 再加入一个新的对象时, 则要先清除最早加入的那个对象
 */
public class CircularQueue<T> {

    /**
     * 启动定时器处理过期对象
     */
    private void startGcTimer() {
        TimerTask gcTimer = new TimerTask() {
            @Override
            public void run() {
                Pair<T, Long> lastPair = peekLastPair();
                if (lastPair == null) {
                    return;
                }
                long currentTimeStamp = System.currentTimeMillis();
                long lastElementTimeStamp = lastPair.getValue();

                long elementLife = currentTimeStamp - lastElementTimeStamp;

                if (elementLife > LIFECYCLE_MILLIS) {
                    System.out.println("删除:" + removeTail());
                }
            }
        };

        Timer timer = new Timer();

        timer.schedule(gcTimer, 100, 100);
    }

    /*
    为方便测试, 修改这些变量的值
     */
    private static final int CAPACITY = 100;// 最大容量
    private static final int LIFECYCLE_MILLIS = 1000;// 对象声明周期(毫秒)

    private Pair<T, Long>[] elementData;// 数据容器
    private int front;// 头指针, 指向最后放入的元素的位置
    private int rare;// 尾指针, 指向最早放入的元素的位置
    private int size;// 当前容量

    public CircularQueue() {
        this.elementData = new Pair[CAPACITY];
        this.front = 0;
        this.rare = 0;
        this.size = 0;
        startGcTimer();
    }

    public int size() {
        return this.size;
    }

    /**
     * 查看队尾元素对
     *
     * @return
     */
    private Pair<T, Long> peekLastPair() {
        return elementData[rare];
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    public T peekLast() {
        Pair<T, Long> resultPair = elementData[rare];
        if (resultPair == null) {
            return null;
        } else {
            return resultPair.getKey();
        }
    }

    /**
     * 查看队头元素
     */
    public T peekHead() {
        Pair<T, Long> resultPair = elementData[front];
        if (resultPair == null) {
            return null;
        } else {
            return resultPair.getKey();
        }
    }

    /**
     * 添加
     *
     * @param t
     */
    public void add(T t) {
        // 构造带有时间戳的元素
        long timeStamp = System.currentTimeMillis();
        Pair<T, Long> element = new Pair<>(t, timeStamp);


        if (size == CAPACITY) {// 容器已满
            // front向前走
            front = (front + 1) % CAPACITY;
            // rare向前走
            rare = (rare + 1) % CAPACITY;
        } else {// 容器未满

            if (size == 0) {// 若容器为空, 不走
            } else {
                // front向前走
                front = (front + 1) % CAPACITY;
            }
            // size增加
            size++;
        }
        // 放入
        elementData[front] = element;

    }

    /**
     * 删除队尾元素
     */
    public T removeTail() {
        if (size == 0) {
            return null;
        }
        // 末尾元素对
        Pair<T, Long> resultPair = elementData[rare];

        // 删除
        elementData[rare] = null;

        if (size == 1) {// 最后一个元素, rear不走
        } else {
            // rare向前走
            rare = (rare + 1) % CAPACITY;
        }
        size--;

        return resultPair.getKey();
    }

}
