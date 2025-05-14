import java.util.*;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    
    List<int[]>[] course;
    int[] dist;
    Set<Integer> summitSet = new HashSet<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n, paths, gates, summits);
        dijkstra();
        
        int[] answer = {INF, INF};
        Arrays.sort(summits);
        for (int summit : summits) {
            if (answer[1] > dist[summit]) {
                answer[1] = dist[summit];
                answer[0] = summit;
            }
        }
        
        return answer;
    }
    
    private void init(int n, int[][] paths, int[] gates, int[] summits) {
        course = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            course[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            course[path[0]].add(new int[]{path[1], path[2]});
            course[path[1]].add(new int[]{path[0], path[2]});
        }
        
        for (int summit : summits) {
            summitSet.add(summit);
        }
        
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        
        for (int gate : gates) {
            dist[gate] = 0;
            pq.add(new int[]{gate, 0});
        }
    }
    
    private void dijkstra() {
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], d = cur[1];
            
            if (dist[node] < d) continue;
            
            for (int[] next : course[node]) {
                int nextNode = next[0], nd = next[1];
                
                if (dist[nextNode] > Math.max(d, nd)) {
                    dist[nextNode] = Math.max(d, nd);
                    if (summitSet.contains(nextNode)) continue;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
}