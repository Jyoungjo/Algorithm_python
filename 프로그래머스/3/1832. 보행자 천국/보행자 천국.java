class Solution {
    int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        // 이동은 오른쪽, 아래로만 진행 -> DP
        // dp[row][col][dir] = 경로 수
        // dir = 0 (진행 방향 오른쪽), 1 (진행 방향 아래)
        // 0인 경우 자유, 1은 벽, 2는 회전 금지
        // 경로 수가 정수 넘어설 수 있으니까 기록할 때 MOD로 나눈 나머지를 기록
        
        int[][][] dp = new int[m][n][2];
        for (int r = 0; r < m; r++) {
            // cityMap[r][0] == 1 -> 더 이상 통행 불가
            if (cityMap[r][0] == 1) break;
            dp[r][0][1] = 1;
        }
        
        for (int c = 0; c < n; c++) {
            // cityMap[0][c] == 1 -> 더 이상 통행 불가
            if (cityMap[0][c] == 1) break;
            dp[0][c][0] = 1;
        }
        
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (cityMap[r][c] == 1) continue;
                
                int fromLeft = dp[r][c - 1][0];
                if (cityMap[r][c - 1] == 0) fromLeft += dp[r][c - 1][1];
                dp[r][c][0] += (fromLeft % MOD);
                
                int fromUp = dp[r - 1][c][1];
                if (cityMap[r - 1][c] == 0) fromUp += dp[r - 1][c][0];
                dp[r][c][1] += (fromUp % MOD);
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}