import java.util.*;

class Node implements Comparable<Node> {
    int idx, cost;
    
    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Node o) {
        if (this.cost > o.cost) return 1;
        else if (this.cost == o.cost) return 0;
        else return -1;
    }
}

class Solution {
    List<Node>[] graph;
    int[] distOfS, distOfA, distOfB;
    int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, s, a, b, fares);
        distOfS = dijkstra(s);
        distOfA = dijkstra(a);
        distOfB = dijkstra(b);
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) min = Math.min(min, distOfS[i] + distOfA[i] + distOfB[i]);
        
        return min;
    }
    
    private void init(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        distOfS = distOfA = distOfB = new int[n + 1];
        
        for (int[] fare : fares) {
            int start = fare[0], end = fare[1], cost = fare[2];
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }
    }
    
    private int[] dijkstra(int start) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (now.cost > dist[now.idx]) continue;
            
            for (Node next : graph[now.idx]) {
                if (dist[next.idx] > now.cost + next.cost) {
                    dist[next.idx] = now.cost + next.cost;
                    pq.add(new Node(next.idx, now.cost + next.cost));
                }
            }
        }
        
        return dist;
    }
}