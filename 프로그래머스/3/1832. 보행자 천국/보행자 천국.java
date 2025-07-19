class Solution {
    int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        
        for (int r = 0; r < m; r++) {
            if (cityMap[r][0] == 1) break;
            dp[r][0][0] = 1;
        }
        
        for (int c = 0; c < n; c++) {
            if (cityMap[0][c] == 1) break;
            dp[0][c][1] = 1;
        }
        
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (cityMap[r][c] == 1) continue;
                
                int fromUp = dp[r - 1][c][0];
                if (cityMap[r - 1][c] == 0) fromUp += dp[r - 1][c][1];
                dp[r][c][0] = (fromUp % MOD);
                
                int fromLeft = dp[r][c - 1][1];
                if (cityMap[r][c - 1] == 0) fromLeft += dp[r][c - 1][0];
                dp[r][c][1] = (fromLeft % MOD);
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}