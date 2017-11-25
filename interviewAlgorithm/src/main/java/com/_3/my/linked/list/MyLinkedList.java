package com._3.my.linked.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCount = 0;// 记录此集合的操作次数, 用于迭代时对比是否并发异常
    private Node<T> beginMaker;
    private Node<T> endMaker;


    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMaker = new Node<>(null, null, null);
        endMaker = new Node<>(null, beginMaker, null);
        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        addBefore(
                getNode(index, 0, size()),
                t
        );
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T set(int index, T newVal) {
        Node<T> p = getNode(index);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;

    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    private void addBefore(Node<T> ptr, T t) {
        Node<T> newNode = new Node<>(t, ptr.prev, ptr);
        newNode.prev.next = newNode;
        ptr.prev = newNode;
        theSize++;
        modCount++;
    }

    private T remove(Node<T> delNode) {
        delNode.next.prev = delNode.prev;

        delNode.prev.next = delNode.next;

        theSize--;

        modCount++;

        return delNode.data;
    }

    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<T> getNode(int index, int lower, int upper) {
        Node<T> ptr;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size() / 2) {
            ptr = beginMaker.next;
            for (int i = 0; i < index; i++) {
                ptr = ptr.next;
            }
        } else {
            ptr = endMaker;
            for (int i = size(); i > index; i--) {
                ptr = ptr.prev;
            }
        }

        return ptr;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMaker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMaker;
        }

        @Override
        public T next() {

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T nextItem = current.data;

            current = current.next;

            okToRemove = true;

            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);

            expectedModCount++;

            okToRemove = false;
        }
    }


}
