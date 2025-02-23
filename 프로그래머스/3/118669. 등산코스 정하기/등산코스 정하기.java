import java.util.*;

class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    int[] mountain;
    List<int[]> result = new ArrayList<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 게이트 = 1, 봉우리 = 2;
        // intensity 최소값을 가지는 봉우리와 intensity 리턴
        // intensity 같으면 봉우리 번호 낮은 것
        
        int[] answer = {};
        init(n, paths, gates, summits);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        int[] dist = new int[n + 1];
        for (int g : gates) {
            dist[g] = 0;
            pq.add(new int[]{g, 0});
        }
        
        dijkstra(pq, dist, n);
        
        Arrays.sort(summits);
        
        int min = Integer.MAX_VALUE, s = 0;
        for (int summit : summits) {
            if (dist[summit] < min) {
                min = dist[summit];
                s = summit;
            }
        }
        
        return new int[]{s, min};
    }
    
    private void dijkstra(PriorityQueue<int[]> pq, int[] dist, int n) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0], w = cur[1];
            
            if (dist[now] < w) continue;
            
            for (int[] info : graph.get(now)) {
                int next = info[0], nW = info[1];
                if (dist[next] > Math.max(w, nW)) {
                    dist[next] = Math.max(w, nW);
                    if (mountain[next] == 2) continue;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
    }
    
    private void init(int n, int[][] paths, int[] gates, int[] summits) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] p : paths) {
            graph.get(p[0]).add(new int[]{p[1], p[2]});
            graph.get(p[1]).add(new int[]{p[0], p[2]});
        }
        
        mountain = new int[n + 1];
        
        for (int s : summits) {
            mountain[s] = 2;
        }
    }
}