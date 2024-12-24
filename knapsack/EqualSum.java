import java.util.Arrays;

public class EqualSum {

    public static int equalSubSetSum(int arr[], int n){

        int totalSum = 0;
        for(int i = 0; i < n; i++){
            totalSum += arr[i];
        }

        int target = totalSum / 2;

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

        int minDiff = Integer.MAX_VALUE;
        for(int i = 0; i <= target; i++){
            if(dp[i]){
                int currDiff = (totalSum - i) - i;
                minDiff = Math.min(minDiff, currDiff);
            }
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int arr[] = {8, 6, 5};
        int result = equalSubSetSum(arr, arr.length);
        System.out.println("result = " + result);
    }

}