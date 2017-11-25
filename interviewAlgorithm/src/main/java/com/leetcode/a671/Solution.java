package com.leetcode.a671;


import com.leetcode.domain.TreeNode;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * <p>
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 * <p>
 * Example 1:
 * Input:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * Output:
 * Merged tree:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * Note: The merging process must start from the root nodes of both trees.
 */

public class Solution {

    /**
     * 参考
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees_ref(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        t1.val += t2.val;
        t1.left = mergeTrees_ref(t1.left, t2.left);
        t1.right = mergeTrees_ref(t1.right, t2.right);
        return t1;
    }

    /**
     * 把t2加到t1中
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        // 过滤
        if (t1 == null) {
            return t2;
        }

        // 以下开始计算
        if (t2 == null) {
            // does nothing
        } else {
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
        }


        return t1;
    }

    /**
     * 构造新结果
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        TreeNode result = null;

        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 != null && t2 == null) {
            result = new TreeNode(t1.val);
            result.left = mergeTrees2(t1.left, null);
            result.right = mergeTrees2(t1.right, null);
        } else if (t1 == null && t2 != null) {
            result = new TreeNode(t2.val);
            result.left = mergeTrees2(null, t2.left);
            result.right = mergeTrees2(null, t2.right);
        } else {
            result = new TreeNode(t1.val + t2.val);
            result.left = mergeTrees2(t1.left, t2.left);
            result.right = mergeTrees2(t1.right, t2.right);
        }

        return result;
    }
}