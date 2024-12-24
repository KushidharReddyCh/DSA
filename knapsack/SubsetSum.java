import java.util.Arrays;
public class SubsetSum {
    /**
     * Given an array arr of n integers and an integer target,
     * determine if there is a subset of the given array with a sum equal to the given target.
     * Input: arr = [1, 2, 7, 3], target = 6
     * Output: True
     * Explanation: There is a subset (1, 2, 3) with sum 6.
    */
    public static boolean subsetSum(int arr[], int idx, int target){
        if(target == 0) return true;
        if(idx == arr.length) return false;

        // skip
        boolean res = subsetSum(arr, idx + 1, target);

        // take
        if(target >= arr[idx]){
            res = res || subsetSum(arr, idx + 1, target - arr[idx]);
        }
        return res;
    }

    public static boolean subsetSum1(int arr[], int n, int target){
        boolean dp[][] = new boolean[n + 1][target + 1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= target; j++){
                if(j == 0){
                    dp[i][j] = true;
                    continue;
                }
                if(i == 0){
                    dp[i][j] = false;
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if(j >= arr[i - 1]){
                    dp[i][j] = dp[i][j] || (dp[i - 1][j - arr[i -1]]);
                }
            }
        }
        return dp[n][target];
    }

    public static boolean subsetSum2(int arr[], int n, int target){
        boolean dp[] = new boolean[target + 1];
        Arrays.fill(dp, false);
        dp[0] = true; // bug fix for 0 in input case
        for(int i = 1; i <= n; i++){
            for(int j = target; j >= 0; j--){
                if(j >= arr[i - 1]){
                    dp[j] = dp[j] || (dp[j - arr[i - 1]]);
                }
            }
        }
        return dp[target];
    }

    /*
    * Given an array arr of n integers and an integer K,
    * count the number of subsets of the given array that have a sum equal to K.
    *  Return the result modulo 109+7.
    * Input: arr = [2, 3, 5, 16, 8, 10], K = 10
    * Output: 3
    * Explanation: The subsets are [2, 8], [10], and [2, 3, 5].
    * */

    public static final int MOD = (int)Math.pow(10, 9) + 7;
    public static int findWays(int arr[], int target) {
        int n = arr.length;
        int dp[] = new int[target + 1];
        dp[0] = 1; // CASE IS IMP IF 0 EXISTS IN THE INPUT
        for(int i = 1; i <= n; i++){
            for(int j = target; j >= 0; j--){
                if(j >= arr[i - 1]){
                    dp[j] += dp[j - arr[i - 1]];
                    dp[j] = dp[j] % MOD;

                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 7, 3};
        System.out.println("res = " + subsetSum2(arr, arr.length, 6));
        System.out.println("res = " + subsetSum2(arr, arr.length, 141));
    }
}