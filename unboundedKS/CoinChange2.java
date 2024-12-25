/*
* You are given an integer array coins representing coins of different
* denominations and an integer amount representing a total amount of money.
* Return the number of combinations that make up that amount.
* If that amount of money cannot be made up by any combination of the coins, return 0.
* You may assume that you have an infinite number of each kind of coin.
* Example 1:
    Input: amount = 5, coins = [1,2,5]
    Output: 4
    Explanation: there are four ways to make up the amount:
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1
*/

public class CoinChange2 {

    public static int coinChange2(int arr[], int idx, int amount){
        if(amount == 0) return 1;
        if(idx == arr.length) return 0;

        // skip
        int res = coinChange2(arr, idx + 1, amount);

        // take
        if(amount >= arr[idx]) {
            res += coinChange2(arr, idx, amount - arr[idx]);
        }
        return res;
    }

    public static int coinChangeDP(int arr[], int amount){

        int n = arr.length;
        int dp[][] = new int[n + 1][amount + 1];

        for(int i = 0; i <= n; i++) { dp[i][0] = 1; }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= amount; j++){
                dp[i][j] += dp[i - 1][j];
                if(j >= arr[i - 1]){
                    dp[i][j] += dp[i][j - arr[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

    public static int coinChangeSpaceOptimized(int arr[], int amount){
        int n = arr.length;
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            // if you iterate from back, it is the bounded knapsack
            for(int j = 1; j <= amount; j++){
                if(j >= arr[i - 1]){
                    dp[j] += dp[j - arr[i - 1]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {

        // Test Case 1
        int arr1[] = {1, 2, 5};
        int amount1 = 5;
        int ans1 = coinChangeDP(arr1, amount1);
        System.out.println("ans 1 = " + ans1);

        // Test Case 2
        int arr2[] = {10};
        int amount2 = 10;
        int ans2 = coinChangeDP(arr2, amount2);
        System.out.println("ans 2 = " + ans2);


        // Test Case 3
        int arr3[] = {3};
        int amount3 = 2;
        int ans3 = coinChangeDP(arr3, amount3);
        System.out.println("ans 3 = " + ans3);

    }

}