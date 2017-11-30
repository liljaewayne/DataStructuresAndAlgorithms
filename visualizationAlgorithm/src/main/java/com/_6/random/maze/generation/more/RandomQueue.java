package com._6.random.maze.generation.more;

import java.util.LinkedList;

/**
 * 因为完全随机的数据结构反应到图上并不是完全随机生成的图, 不同点其实就是从队首或队尾的随机, 这样才能生成更随机化的解
 *
 * @param <E>
 */
public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<E>();
    }

    public void add(E e) {
        if (Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove() {
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");


        if (Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return size() == 0;
    }
}