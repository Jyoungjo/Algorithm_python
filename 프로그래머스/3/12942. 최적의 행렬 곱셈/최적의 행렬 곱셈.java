import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int len = matrix_sizes.length, INF = 987654321;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) Arrays.fill(dp[i], INF);
        for (int i = 0; i < len; i++) dp[i][i] = 0;
        
        for (int l = 1; l < len; l++) {
            for (int s = 0; s < len - l; s++) {
                int e = s + l;
                for (int m = s; m < e; m++) {
                    dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m + 1][e]
                        + matrix_sizes[s][0] * matrix_sizes[m][1] * matrix_sizes[e][1]);
                }
            }
        }
        
        return dp[0][len - 1];
    }
}