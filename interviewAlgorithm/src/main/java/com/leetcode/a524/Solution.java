package com.leetcode.a524;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a string and a string dictionary,
 * find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 * <p>
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * <p>
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * <p>
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
    }

    /**
     * O(N)
     *
     * @param metaElements
     * @param t
     * @return
     */
    public boolean isDictional(String metaElements, String t) {
        if (t.length() > metaElements.length()) {
            return false;
        }
        int nowIndex = 0;
        for (int i = 0; i < t.length(); i++) {
            int position = metaElements.indexOf(t.charAt(i), nowIndex);
            if (position < 0) {
                return false;
            } else {
                nowIndex = position + 1;
            }
        }
        return true;
    }


    /**
     * O(N^2)
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {

        // 不用优先队列也能返回结果, 只需用一个变量维护
        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (s1, s2) ->
                        s1.length() == s2.length() ? s1.compareTo(s2) : s2.length() - s1.length()
        );

        for (String str : d) {
            if (isDictional(s, str)) {
                minHeap.add(str);
            }
        }

        if (minHeap.size() == 0) {
            return "";
        }

        return minHeap.peek();
    }
}
