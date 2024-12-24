import java.util.*;

public class KnapSack {
    public static int knapSack01(int wt[], int val[], int n, int W){
        int dp[][] = new int[n + 1][W + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                dp[i][j] = dp[i - 1][j];
                if(j >= wt[i - 1]){
                    dp[i][j] = Math.max(dp[i][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
                }
            }
        }
        return dp[n][W];
    }

    public static int knapSack01_spaceOptimized(int wt[], int val[], int n, int W){
        int dp[] = new int[W + 1];
        for(int i = 1; i <= n; i++){
            for(int j = W; j >= 0; j--){
                if(j >= wt[i - 1]){
                    dp[j] = Math.max(dp[j], val[i - 1] + dp[j - wt[i - 1]]);
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int val[] = {10, 40, 30, 50};
        int wt[] = {5, 4, 6, 3};
        int res = knapSack01(wt, val, wt.length, 10);
        System.out.println("result = " + res);
    }

}