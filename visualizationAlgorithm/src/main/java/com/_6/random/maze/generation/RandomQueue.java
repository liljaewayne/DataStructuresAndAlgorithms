package com._6.random.maze.generation;

import java.util.ArrayList;

/**
 * 需求, 提供add和remove方法, 每次remove返回容器中的随机一个值
 *
 * @param <E>
 */
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue() {
        queue = new ArrayList<E>();
    }

    public void add(E e) {
        queue.add(e);
    }

    public E remove() {
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");

        int randIndex = (int) (Math.random() * queue.size());

        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);

        return randElement;
    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return size() == 0;
    }
}