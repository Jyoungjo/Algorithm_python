import java.util.*;

class Solution {
    int[][] dp;
    int[][] matrix;
    int N;
    
    public int solution(int[][] matrix_sizes) {
        N = matrix_sizes.length;
        matrix = matrix_sizes;
        dp = new int[N + 1][N + 1];
        return cal(0, N);
    }
    
    private int cal(int s, int e) {
        if (e - s == 1) return 0;
        
        int res = 987654321;
        for (int m = s + 1; m < e; m++) {
            int left = memo(s, m), right = memo(m, e);
            int cur = matrix[s][0] * matrix[m][0] * matrix[e - 1][1];
            res = Math.min(res, left + cur + right);
        }
        return res;
    }
    
    private int memo(int s, int e) {
        if (dp[s][e] == 0) dp[s][e] = cal(s, e);
        return dp[s][e];
    }
}