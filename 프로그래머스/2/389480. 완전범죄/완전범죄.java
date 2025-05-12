import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        /*
            리턴값 : A도둑이 남긴 흔적의 누적 개수 최소값
            A가 훔치느냐 B가 훔치느냐
            A가 훔쳤을 때의 A의 흔적 / B가 훔쳤을 때의 A의 흔적
            dp 풀이 진행
            dp[흔적 개수][B가 훔쳤을 때] = A의 흔적 최소값
            
        */
        int INF = 987654321;
        int[][] dp = new int[info.length + 1][m + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][m] = 0;
        
        for (int i = 1; i <= info.length; i++) {
            int a = info[i - 1][0], b = info[i - 1][1];
            
            for (int j = m; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                
                if (j - b > 0) {
                    dp[i][j - b] = Math.min(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        
        int answer = INF;
        for (int i = 1; i <= m; i++) {
            if (dp[info.length][i] < n) answer = Math.min(answer, dp[info.length][i]);
        }
        return answer == INF ? -1 : answer;
    }
}