import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        long[] dp = new long[n + 1];
        List<Integer> nums = new ArrayList<>();
        
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            dp[i] = dp[i - 1] * i;
        }
        
        k--;
        for (int i = 0; i < n; i++) {
            int idx = (int) (k / dp[n - 1 - i]);
            answer[i] = nums.get(idx);
            nums.remove(idx);
            k %= dp[n - 1 - i];
        }
        
        return answer;
    }
}