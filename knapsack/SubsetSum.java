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
        dp[0] = false;
        for(int i = 1; i <= n; i++){
            dp[0] = true;
            for(int j = target; j >=0; j--){
                if(j >= arr[i - 1]){
                    dp[j] = dp[j] || (dp[j - arr[i - 1]]);
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