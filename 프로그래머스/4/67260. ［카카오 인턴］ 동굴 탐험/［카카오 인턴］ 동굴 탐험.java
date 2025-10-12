import java.util.*;

class Solution {
    int N;
    List<Integer>[] tree;
    Map<Integer, Integer> orderMap = new HashMap<>();
    boolean wrong = false;
    
    public boolean solution(int n, int[][] path, int[][] order) {
        init(n, path, order);
        if (wrong) return false;
        
        return search(0);
    }
    
    private boolean search(int cur) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> waiting = new HashMap<>();
        
        q.add(cur);
        visited.add(cur);
        
        while (!q.isEmpty()) {
            int now = q.poll();
            
            if (waiting.containsKey(now)) {
                int next = waiting.get(now);
                q.add(next);
                visited.add(next);
            }
            
            for (int next : tree[now]) {
                if (visited.contains(next)) continue;
                
                if (!orderMap.containsKey(next)) {
                    visited.add(next);
                    q.add(next);
                    continue;
                }
                
                if (visited.contains(orderMap.get(next))) {
                    q.add(next);
                    visited.add(next);
                } else waiting.put(orderMap.get(next), next);
            }
        }
        
        if (visited.size() != N) return false;
        return true;
    }
    
    private void init(int n, int[][] path, int[][] order) {
        N = n;
        tree = new List[n];
        
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] p : path) {
            tree[p[0]].add(p[1]);
            tree[p[1]].add(p[0]);
        }
        
        for (int[] o : order) orderMap.put(o[1], o[0]);
        if (orderMap.containsKey(0)) {
            wrong = true;
            return;
        }
    }
}