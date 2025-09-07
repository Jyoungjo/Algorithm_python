import java.util.*;

class Solution {
    final int MAX = 987654321;
    int N, M;
    List<Integer>[] edges;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        N = n; M = m;
        edges = new List[n + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
        for (int[] edge : edge_list) {
            edges[edge[0]].add(edge[1]);
            edges[edge[1]].add(edge[0]);
        }
        
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) Arrays.fill(dp[i], MAX);
        dp[0][gps_log[0]] = 0;
        
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                int prev = dp[i - 1][j], goal = gps_log[i];
                
                for (int next : edges[j]) {
                    if (next == goal) dp[i][next] = Math.min(dp[i][next], prev);
                    else dp[i][next] = Math.min(dp[i][next], prev + 1);
                }
            }
        }
        
        return dp[k - 1][gps_log[gps_log.length - 1]] == MAX ? -1 : dp[k - 1][gps_log[gps_log.length - 1]];
    }
}