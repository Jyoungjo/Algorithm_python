import java.util.*;

class Solution {
    public void bfs(int n, int[][] computers, boolean[] visited, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            
            for (int i = 0; i < n; i++) {
                if (!visited[i] && computers[now][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(n, computers, visited, i);
                answer++;
            }
        }
        return answer;
    }
}