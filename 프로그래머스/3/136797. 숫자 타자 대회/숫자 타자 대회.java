import java.util.*;


class Solution {
    int[][] keyboard = {
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1},
    };
    int[][][] dp;
    int n;
    String tmp;
    
    public int solution(String numbers) {
        n = numbers.length();
        tmp = numbers;
        dp = new int[n][10][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        return findVal(0, 4, 6);
    }
    
    private int findVal(int idx, int l, int r) {
        if (idx == n) return 0;
        if (dp[idx][l][r] != -1) return dp[idx][l][r];
        
        int num = tmp.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;
        
        if (num != r) result = Math.min(result, keyboard[l][num] + findVal(idx + 1, num, r));
        if (num != l) result = Math.min(result, keyboard[num][r] + findVal(idx + 1, l, num));
        
        dp[idx][l][r] = result;
        return result;
    }
}