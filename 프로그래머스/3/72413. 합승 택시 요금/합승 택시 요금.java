import java.util.*;

class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    boolean[] visited;
    int size = 0;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        /*
            어떠한 길을 공통으로 사용했을 때, 공통 사용 길의 마지막 지점으로 부터 각 목표하는 지점까지의 최소 길이 탐색
            각 노드만큼 다익스트라 탐색하면 노드까지 가는 길 최소 거리 나옴
            그럼 그 노드까지의 최소거리를 공동 길로 하여 탐색한 값 기록
            탐색은 dijkstra
        */
        
        int answer = Integer.MAX_VALUE;
        size = n;
        init(size, fares);
        int[] dist = dijkstra(s);
        for (int i = 1; i <= size; i++) {
            answer = Math.min(answer, dist[i] + dijkstra(i, a, b));
        }
        return answer;
    }
    
    private int[] dijkstra(int start) {
        int[] dist = new int[size + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(node -> node[1]));
        pq.add(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int now = info[0], d = info[1];
            if (dist[now] < d) continue;
            
            for (int[] nInfo : graph.get(now)) {
                int next = nInfo[0], dd = nInfo[1];
                if (dist[next] > dist[now] + dd) {
                    dist[next] = dist[now] + dd;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
        
        return dist;
    }
    
    private int dijkstra(int start, int a, int b) {
        int[] dist = new int[size + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(node -> node[1]));
        pq.add(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int now = info[0], d = info[1];
            if (dist[now] < d) continue;
            
            for (int[] nInfo : graph.get(now)) {
                int next = nInfo[0], dd = nInfo[1];
                if (dist[next] > dist[now] + dd) {
                    dist[next] = dist[now] + dd;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
        
        return dist[a] + dist[b];
    }
    
    private void init(int n, int[][] fares) {
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] fare : fares) {
            graph.get(fare[0]).add(new int[]{fare[1], fare[2]});
            graph.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }
    }
}