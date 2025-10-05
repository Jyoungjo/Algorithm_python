import java.util.*;

class Solution {
    int len;
    int[] dp;
    
    public int solution(int[] money) {
        len = money.length;
        dp = new int[len];
        int answer = Integer.MIN_VALUE;
        
        for (int i = 0; i <= 1; i++) {
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[i] = money[i];
            for (int j = i + 1; j < len + i - 1; j++) {
                if (j == i + 1) dp[j] = Math.max(dp[j - 1], money[j]);
                else dp[j] = Math.max(dp[j - 1], dp[j - 2] + money[j]);
            }
            answer = Math.max(answer, dp[len + i - 2]);
        }
        
        return answer;
    }
}