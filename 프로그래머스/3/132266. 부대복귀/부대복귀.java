import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        init(n, roads);
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        findMinTime(n, destination, dist);
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]] == Integer.MAX_VALUE ? -1 : dist[sources[i]];
        }
        
        return answer;
    }
    
    private void findMinTime(int n, int start, int[] dist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0], d = cur[1];
            
            for (int next : graph.get(now)) {
                if (dist[next] > d + 1) {
                    dist[next] = d + 1;
                    pq.add(new int[]{next, d + 1});
                }
            }
        }
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