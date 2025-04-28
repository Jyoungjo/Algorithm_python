class Solution {
    final int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[m][n][2];
        for (int r = 0; r < m; r++) {
            if (cityMap[r][0] == 1) break;
            dp[r][0][1] = 1;
        }
        
        for (int c = 0; c < n; c++) {
            if (cityMap[0][c] == 1) break;
            dp[0][c][0] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) continue;
                
                int dirFromUp = dp[i - 1][j][1];
                if (cityMap[i - 1][j] == 0) dirFromUp += dp[i - 1][j][0];
                dp[i][j][1] += (dirFromUp % MOD);
                
                int dirFromLeft = dp[i][j - 1][0];
                if (cityMap[i][j - 1] == 0) dirFromLeft += dp[i][j - 1][1];
                dp[i][j][0] += (dirFromLeft % MOD);
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}