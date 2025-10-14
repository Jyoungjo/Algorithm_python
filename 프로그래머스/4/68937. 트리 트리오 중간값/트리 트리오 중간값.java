import java.util.*;

class Result {
    List<Integer> leafNodes;
    int depth;
    
    Result(List<Integer> leafNodes, int depth) {
        this.leafNodes = leafNodes;
        this.depth = depth;
    }
}

class Solution {
    List<Integer>[] tree;
    int N;
    
    public int solution(int n, int[][] edges) {
        init(n, edges);
        return solve();
    }
    
    private int solve() {
        int cand = bfs(1);
        Result res1 = findMaxDist(cand);
        if (res1.leafNodes.size() > 1) return res1.depth;
        
        Result res2 = findMaxDist(res1.leafNodes.get(0));
        if (res2.leafNodes.size() > 1) return res2.depth;
        return res2.depth - 1;
    }
    
    private int bfs(int start) {
        int cand = 0;
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            
            if (tree[now].size() == 1) cand = now;
            
            for (int next : tree[now]) {
                if (visited[next]) continue;
                
                visited[next] = true;
                q.add(next);
            }
        }
        
        return cand;
    }
    
    private Result findMaxDist(int start) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        
        q1.add(start);
        visited[start] = true;
        
        int depth = 0;
        List<Integer> list = null;
        
        while (true) {
            while (!q1.isEmpty()) {
                int now = q1.poll();
            
                for (int next : tree[now]) {
                    if (visited[next]) continue;
                    
                    visited[next] = true;
                    q2.add(next);
                }
            }
            
            if (q2.isEmpty()) break;
            
            depth++;
            list = new ArrayList<>(q2);
            
            while (!q2.isEmpty()) q1.add(q2.poll());
        }
        
        return new Result(list, depth);
    }
    
    private void init(int n, int[][] edges) {
        N = n;
        
        tree = new List[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
    }
}