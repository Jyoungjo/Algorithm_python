class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        if (len == 1) return sticker[0];
        
        for (int i = 0; i < 2; i++) {
            int[] dp = new int[len - 1];
            dp[0] = sticker[i];
            
            for (int j = 1; j < len - 1; j++) {
                if (j == 1) dp[j] = Math.max(dp[j - 1], sticker[i + j]);
                else dp[j] = Math.max(dp[j - 1], dp[j - 2] + sticker[i + j]);
            }
            
            answer = Math.max(answer, dp[len - 2]);
        }
        
        return answer;
    }
}