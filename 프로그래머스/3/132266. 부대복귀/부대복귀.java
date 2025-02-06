import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        init(n, roads);
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = findMinTime(n, destination, sources[i]);
        }
        
        return answer;
    }
    
    private int findMinTime(int n, int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        q.add(new int[]{start, 0});
        dist[start] = 0;
        
        if (start == end) return 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int now = cur[0], d = cur[1];
            visited[now] = true;
            
            for (int next : graph.get(now)) {
                if (visited[next]) continue;
                if (next == end) return d + 1;
                
                dist[next] = d + 1;
                q.add(new int[]{next, d + 1});
            }
        }
        
        return -1;
    }
    
    private void init(int n, int[][] roads) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
    }
}