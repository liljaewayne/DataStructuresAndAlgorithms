package com._4.bst;


import java.nio.BufferUnderflowException;

public class BinarySearchTree<T extends Comparable<? super T>> {

    /**
     * 用嵌套类表示树的节点
     *
     * @param <T>
     */
    private static class BinaryTreeNode<T> {
        public BinaryTreeNode(T element) {
            this(element, null, null);
        }

        public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

    }

    private BinaryTreeNode<T> root;

    public BinarySearchTree() {
        makeEmpty();
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }


    public void insert(T t) {
        root = insert(t, root);
    }

    public void remove(T t) {
        root = remove(t, root);
    }

    public void printTree() {
        // TODO
    }


    private boolean contains(T t, BinaryTreeNode<T> tree) {
        // TODO
        return false;
    }


    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> tree) {
        // TODO
        return null;
    }


    private BinaryTreeNode<T> findMax(BinaryTreeNode<T> tree) {
        // TODO
        return null;
    }


    private BinaryTreeNode<T> insert(T t, BinaryTreeNode<T> tree) {
        // TODO
        return null;
    }

    private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> tree) {
        // TODO: 2017/7/14 0014
        return null;
    }

    private void printTree(BinaryTreeNode tree) {
        // TODO: 2017/7/14 0014
    }


}

