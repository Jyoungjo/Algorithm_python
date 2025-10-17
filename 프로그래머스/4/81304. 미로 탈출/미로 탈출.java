import java.util.*;

class Node implements Comparable<Node> {
    int to, w, state;
    
    Node(int to, int w, int state) {
        this.to = to;
        this.w = w;
        this.state = state;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}

class Solution {
    int N;
    List<Node>[] graph, rGraph;
    Map<Integer, Integer> trap = new HashMap<>();
    int[][] dist;
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        init(n, roads, traps);
        escape(start, end);
        return getMinVal(end);
    }
    
    private int getMinVal(int end) {
        int result = Integer.MAX_VALUE;
        for (int val : dist[end]) result = Math.min(result, val);
        return result;
    }
    
    private void escape(int start, int end) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0));
        dist[start][0] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int to = now.to, w = now.w, state = now.state;
            
            if (to == end) return;
            
            int f1 = 0;
            if (trap.containsKey(to)) {
                if ((state & trap.get(to)) != 0) f1 = 1;
            }
            
            int canForward = f1;
            for (Node next : graph[to]) {
                canForward = f1;
                int nState = state;
                if (trap.containsKey(next.to)) {
                    if ((state & trap.get(next.to)) != 0) canForward ^= 1;
                    nState ^= trap.get(next.to);
                }
                
                if (canForward == 0) {
                    if (dist[next.to][state] > w + next.w) {
                        dist[next.to][state] = w + next.w;
                        pq.add(new Node(next.to, w + next.w, nState));
                    }
                }
            }
            
            for (Node next : rGraph[to]) {
                canForward = f1;
                int nState = state;
                if (trap.containsKey(next.to)) {
                    if ((state & trap.get(next.to)) != 0) canForward ^= 1;
                    nState ^= trap.get(next.to);
                }
                
                if (canForward == 1) {
                    if (dist[next.to][state] > w + next.w) {
                        dist[next.to][state] = w + next.w;
                        pq.add(new Node(next.to, w + next.w, nState));
                    }
                }
            }
        }
    }
    
    private void init(int n, int[][] roads, int[] traps) {
        N = n;
        graph = new List[n + 1]; rGraph = new List[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            rGraph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            int from = road[0], to = road[1], w = road[2];
            graph[from].add(new Node(to, w, 0));
            rGraph[to].add(new Node(from, w, 0));
        }
        
        for (int i = 0; i < traps.length; i++) trap.put(traps[i], 1 << (i + 1));
        
        dist = new int[n + 1][1 << trap.size() + 1];
        for (int i = 1; i <= n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
    }
}