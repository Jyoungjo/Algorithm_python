import java.util.*;

class Solution {
    int[][] dp;
    int[][] matrix;
    int len;
    
    public int solution(int[][] matrix_sizes) {
        len = matrix_sizes.length;
        matrix = matrix_sizes;
        dp = new int[len + 1][len + 1];
        return cal(0, len);
    }
    
    private int cal(int s, int e) {
        if (e - s == 1) return 0;
        
        int result = 987654321;
        for (int m = s + 1; m < e; m++) {
            int l = memo(s, m), r = memo(m, e);
            int cur = matrix[s][0] * matrix[m][0] * matrix[e - 1][1];
            result = Math.min(result, l + cur + r);
        }
        
        return result;
    }
    
    private int memo(int s, int e) {
        if (dp[s][e] == 0) dp[s][e] = cal(s, e);
        return dp[s][e];
    }
}