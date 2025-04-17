import java.util.*;

class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    
    public int solution(int N, int[][] road, int K) {
        init(N, road);
        return dijkstra(1, N, K);
    }
    
    private int dijkstra(int start, int N, int K) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0], d = now[1];
            
            if (d > dist[node]) continue;
            
            for (int[] next : graph.get(node)) {
                if (dist[next[0]] > next[1] + d) {
                    dist[next[0]] = next[1] + d;
                    pq.add(new int[]{next[0], next[1] + d});
                }
            }
        }
        
        return (int) Arrays.stream(dist).filter(a -> a <= K).count();
    }
    
    private void init(int N, int[][] road) {
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : road) {
            graph.get(r[0]).add(new int[]{r[1], r[2]});
            graph.get(r[1]).add(new int[]{r[0], r[2]});
        }
    }
}