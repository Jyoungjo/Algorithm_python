class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        if (sticker.length == 1) return sticker[0];
        for (int i = 0; i <= 1; i++) {
            int[] dp = new int[sticker.length - 1];
            dp[0] = sticker[i];
            for (int j = 1; j < sticker.length - 1; j++) {
                if (j == 1) dp[j] = Math.max(dp[j - 1], sticker[j + i]);
                else dp[j] = Math.max(dp[j - 2] + sticker[i + j], dp[j - 1]);
            }
            
            answer = Math.max(answer, dp[sticker.length - 2]);
        }
        return answer;
    }
}