import java.util.*;

class Node {
    int idx, parent;
    long w;
    
    public Node(int idx, int parent, long w) {
        this.idx = idx;
        this.parent = parent;
        this.w = w;
    }
}

class Solution {
    List<List<Integer>> tree = new ArrayList<>();
    boolean[] visited;
    long[] b;
    int size = 0;
    
    public long solution(int[] a, int[][] edges) {
        init(a, edges);
        int idx = getLeafNode(0);
        return dfs(idx, a);
    }
    
    private long dfs(int start, int[] a) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(start, start, 0));
        
        while (!stack.isEmpty()) {
            Node now = stack.pop();
            
            if (!visited[now.idx]) {
                visited[now.idx] = true;
                
                stack.push(now);
                for (int next : tree.get(now.idx)) {
                    if (visited[next]) continue;
                    stack.push(new Node(next, now.idx, 0));
                }
            } else {
                visited[now.idx] = false;
                if (now.idx == start) return b[now.idx] != 0 ? -1 : now.w;
                if (b[now.idx] == 0) {
                    if (!stack.isEmpty()) stack.peekLast().w += now.w;
                    continue;
                }
                
                long weight = b[now.idx];
                b[now.idx] = 0;
                b[now.parent] += weight;
                now.w += Math.abs(weight);
                
                stack.peekLast().w += now.w;
            }
        }
        return -1;
    }
    
    private int getLeafNode(int start) {
        if (tree.get(start).size() == 1) return start;
        visited[start] = true;
        
        for (int next : tree.get(start)) {
            if (visited[next]) continue;
            int leafNode = getLeafNode(next);
            visited[start] = false;
            return leafNode;
        }
        
        visited[start] = false;
        return start;
    }
    
    private void init(int[] a, int[][] edges) {
        size = a.length;
        visited = new boolean[size];
        b = new long[size];
        for (int i = 0; i < size; i++) {
            tree.add(new ArrayList<>());
            b[i] = a[i];
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
    }
}