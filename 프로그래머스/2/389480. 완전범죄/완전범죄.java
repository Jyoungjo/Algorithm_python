import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int size = info.length, INF = 100000;
        int[][] dp = new int[size + 1][m];
        for (int i = 0; i <= size; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for (int i = 1; i <= size; i++) {
            int a = info[i - 1][0], b = info[i - 1][1];
            
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                
                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            result = Math.min(result, dp[size][i]);
        }
        
        return result < n ? result : -1;
    }
}