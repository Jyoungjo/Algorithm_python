import java.util.*;

class Solution {
    List<Integer>[] graph;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        init(n, edge_list);
        final int INF = 987654321;
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) Arrays.fill(dp[i], INF);
        dp[0][gps_log[0]] = 0;
        
        for (int i = 1; i < k; i++) {
            for (int v = 1; v <= n; v++) {
                int prev = dp[i - 1][v], goal = gps_log[i];
                
                for (int next : graph[v]) {
                    if (next == goal) dp[i][next] = Math.min(dp[i][next], prev);
                    else dp[i][next] = Math.min(dp[i][next], prev + 1);
                }
            }
        }
        
        int end = gps_log[gps_log.length - 1];
        return dp[k - 1][end] == INF ? -1 : dp[k - 1][end];
    }
    
    private void init(int n, int[][] edge_list) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edge_list) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }
}