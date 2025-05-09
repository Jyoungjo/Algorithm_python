import java.util.*;

class Solution {
    List<Integer>[] graph;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        makeGraph(n, edge_list);
        int[][] dp = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], 987654321);
        }
        dp[0][gps_log[0]] = 0;
        
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                int prev = dp[i - 1][j];
                int goal = gps_log[i];
                
                for (int next : graph[j]) {
                    if (next == goal) dp[i][next] = Math.min(dp[i][next], prev);
                    else dp[i][next] = Math.min(dp[i][next], prev + 1);
                }
            }
        }
        
        int end = gps_log[gps_log.length - 1];
        return dp[k - 1][end] == 987654321 ? -1 : dp[k - 1][end];
    }
    
    private void makeGraph(int n, int[][] edge_list) {
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edge_list) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }
}