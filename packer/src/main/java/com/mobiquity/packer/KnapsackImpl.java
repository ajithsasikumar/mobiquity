package com.mobiquity.packer;

/*

This is a reference class which represents a well known alternative
to implement the solution called knapsack problem

This implementation is not utilized in the api
 */

public class KnapsackImpl {

    /*
          wt =>  array of weights
          val => array of values
          w => Max weight cspscity
          dp => 2D array to store the maximum value that can be achieved for each combination of items and weights
     */
    public static int knapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

}
