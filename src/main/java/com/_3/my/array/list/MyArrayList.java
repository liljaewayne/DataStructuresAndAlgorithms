package com._3.my.array.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public T set(int index, T newVal) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        // 满了, 就扩容
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        // 把该插入位置的元素和其后面的元素都向后挪一位
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        // 插入
        theItems[index] = t;
        // 计数
        theSize++;
    }

    public T remove(int index) {

        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T removeItem = theItems[index];

        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }

        theSize--;

        return removeItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<T>();
    }

    private class ArrayListIterator<T> implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) theItems[current++];
        }

        @Override
        public void remove() {
            current--;
            MyArrayList.this.remove(current);
        }
    }

}
