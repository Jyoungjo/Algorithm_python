import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int R = board.length, C = board[0].length;
        int[][] dp = new int[R][C];
        
        int answer = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 1) {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else {
                        dp[i][j] = Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                        ) + 1;
                    }
                }
                answer = Math.max(answer, dp[i][j] * dp[i][j]);
            }
        }
        
        return answer;
    }
}