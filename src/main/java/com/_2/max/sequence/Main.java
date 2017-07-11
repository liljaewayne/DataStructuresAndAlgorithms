package com._2.max.sequence;

public class Main {
    public static void main(String[] args) {
        int result = maxSubSum4(new int[]{4, -3, 5, -2, -1, 2, 6, -2});

        System.out.println(result);
    }

    /**
     * O(n^3)
     *
     * @param arr
     * @return
     */
    public static int maxSubSum1(int[] arr) {
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * O(n^2)
     *
     * @param arr
     * @return
     */
    public static int maxSubSum2(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int thisSum = 0;

            for (int j = i; j < arr.length; j++) {
                thisSum += arr[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }

            }

        }

        return maxSum;
    }


    private static int maxSubSum3(int[] arr) {
        return maxSumRec(arr, 0, arr.length - 1);
    }

    private static int maxSumRec(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left] > 0 ? arr[left] : 0;
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(arr, left, center);
        int maxRightSum = maxSumRec(arr, center + 1, right);


        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];

            maxLeftBorderSum = Math.max(maxLeftBorderSum, leftBorderSum);
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];

            maxRightBorderSum = Math.max(maxRightBorderSum, rightBorderSum);
        }

        int maxCrossOverSum = maxLeftBorderSum + maxRightBorderSum;

        return max3(maxLeftSum, maxRightSum, maxCrossOverSum);
    }

    /**
     * 分治算法
     * O(nlogn)
     *
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    private static int max3(int num1, int num2, int num3) {
        int max = Math.max(num1, num2);
        max = Math.max(max, num3);
        return max;
    }

    /**
     * 在线处理(on-line algorithm 联机算法)
     * O(n)
     *
     * @param arr
     * @return
     */
    public static int maxSubSum4(int[] arr) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = 0; i < arr.length; i++) {
            thisSum += arr[i];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }

}
