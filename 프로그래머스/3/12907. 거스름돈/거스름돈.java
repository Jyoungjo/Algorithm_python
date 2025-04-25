import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        Arrays.sort(money);
        for (int i = 0; i < money.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j - money[i] < 0) continue;
                dp[j] += dp[j - money[i]];
            }
        }
        return dp[n] % 1000000007;
    }
}