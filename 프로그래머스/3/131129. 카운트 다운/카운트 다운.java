class Solution {
    int[][] dp;
    final int MAX = Integer.MAX_VALUE;
    
    public int[] solution(int target) {
        dp = new int[target + 1][2];
        for (int i = 0; i < target + 1; i++) {
            dp[i] = new int[]{MAX, MAX};
        }
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        for (int i = 0; i < target + 1; i++) {
            hit(i, 1, 20, target); // 싱글
            hit(i, 2, 40, target); // 더블
            hit(i, 3, 60, target); // 트리플
            hit(i, 50, target); // 불
        }
        
        return dp[target];
    }
    
    private void hit(int now, int s, int e, int target) {
        for (int i = s; i <= e; i += s) {
            if (now + i > target) break;
            
            if (dp[now][0] + 1 < dp[now + i][0]) {
                dp[now + i][0] = dp[now][0] + 1;
                if (s == 1) dp[now + i][1] = dp[now][1] + 1;
                else dp[now + i][1] = dp[now][1];
            } else if (dp[now][0] + 1 == dp[now + i][0]) {
                int singleOrBull = s == 1 ? dp[now][1] + 1 : dp[now][1];
                if (singleOrBull > dp[now + i][1]) dp[now + i][1] = singleOrBull;
            }
        }
    }
    
    private void hit(int now, int bull, int target) {
        if (now + bull > target) return;
        
        if (dp[now][0] + 1 < dp[now + bull][0]) {
            dp[now + bull][0] = dp[now][0] + 1;
            dp[now + bull][1] = dp[now][1] + 1;
        } else if (dp[now][0] + 1 == dp[now + bull][0] && dp[now][1] + 1 > dp[now + bull][1]) {
            dp[now + bull][1] = dp[now][1] + 1;
        }
    }
}