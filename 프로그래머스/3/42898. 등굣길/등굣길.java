import java.util.*;

class Solution {
    int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n][m];
        int denominator = 1000000007;
        
        for (int[] x : dp) {
            Arrays.fill(x, 1);
        }
        
        for (int[] puddle : puddles) {
            int py = puddle[0] - 1, px = puddle[1] - 1;
            dp[px][py] = 0;
            
            if (py == 0) {
                for (int ii = px; ii < n; ii++) {
                    dp[ii][py] = 0;
                }
            }
            
            if (px == 0) {
                for (int jj = py; jj < m; jj++) {
                    dp[px][jj] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] != 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % denominator;
                }
            }
        }
        
        return dp[n - 1][m - 1];
    }
}