class Solution {
    public int solution(int n) {
        final long MOD = 1000000007L;
        
        long[] dp = new long[n + 1];
        dp[2] = 3;
        long sum = 0;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * dp[2] + sum * 2 + 2) % MOD;
            sum += dp[i - 2];
        }
        
        return (int) dp[n];
    }
}