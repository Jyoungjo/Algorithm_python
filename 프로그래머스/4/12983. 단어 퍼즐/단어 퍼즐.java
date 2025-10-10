import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE;
    
    public int solution(String[] strs, String t) {
        int[] dp = new int[t.length() + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < t.length(); i++) {
            if (dp[i] == INF) continue;
            
            for (String str : strs) {
                if (t.startsWith(str, i)) {
                    int nextIdx = i + str.length();
                    dp[nextIdx] = Math.min(dp[i] + 1, dp[nextIdx]);
                }
            }
        }
        
        return dp[t.length()] == INF ? -1 : dp[t.length()];
    }
}