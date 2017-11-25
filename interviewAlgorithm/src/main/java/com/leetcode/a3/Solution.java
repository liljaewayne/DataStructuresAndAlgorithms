package com.leetcode.a3;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcacbcbb"));
    }

    /**
     * 滑动窗口, 复杂度O(n)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int back = 0;

        int currentStringLen = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            String subStr = s.substring(back, i + 1);
            int tmpLen = subStr.length();
            char fChar = s.charAt(i);

            if (tmpLen > 0) {
                int index = subStr.indexOf(fChar);
                if (index >= 0 && index < (tmpLen - 1)) {
                    subStr = subStr.substring(index + 1);
                    back = back + index + 1;
                    tmpLen = subStr.length();
                }
            }

            if (tmpLen > currentStringLen) {
                currentStringLen = tmpLen;
            }
        }
        return currentStringLen;
    }

}
