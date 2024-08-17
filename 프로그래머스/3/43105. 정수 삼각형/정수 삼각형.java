import java.util.*;

class Solution {
    int[][] dp;
    
    public int solution(int[][] triangle) {
        int h = triangle.length;
        dp = new int[h][h];
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < h; i++) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + triangle[i][j]);
                dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i - 1][j] + triangle[i][j + 1]);
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int x : dp[h - 1]) {
            if (max < x) max = x;
        }
        
        return max;
    }
}