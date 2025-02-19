import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int size = elements.length;
        int[][] dp = new int[size][1000]; // 부분수열 개수, 순서 = 합
        Set<Integer> result = new HashSet<>();
        
        for (int i = 0; i < size; i++) {
            dp[0][i] = elements[i];
            result.add(elements[i]);
        }
        
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dp[i][j] = dp[0][j] + dp[i - 1][(j + 1) % size];
                if (dp[i][j] != 0) result.add(dp[i][j]);
            }
        }
        
        return result.size();
    }
}