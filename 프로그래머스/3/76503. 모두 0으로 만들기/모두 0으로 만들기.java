import java.util.*;

class Node {
    int idx;
    long weight;
    Node parent;
    
    public Node(int idx, long weight, Node parent) {
        this.idx = idx;
        this.weight = weight;
        this.parent = parent;
    }
}

class Solution {
    List<Integer>[] tree;
    int len;

    public long solution(int[] a, int[][] edges) {
        len = a.length;
        tree = new List[len];
        for (int i = 0; i < a.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        
        return dfs(0, a);
    }
    
    private long dfs(int start, int[] a) {
        long result = 0;
        Deque<Node> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[len];
        stack.push(new Node(start, a[start], null));
        
        while (!stack.isEmpty()) {
            Node now = stack.pop();
            
            if (!visited[now.idx]) {
                visited[now.idx] = true;
                stack.push(now);
                
                for (int next : tree[now.idx]) {
                    if (visited[next]) continue;
                    stack.push(new Node(next, a[next], now));
                }
            } else {
                if (now.idx == start) return now.weight == 0 ? result : -1;
                
                now.parent.weight += now.weight;
                result += Math.abs(now.weight);
                now.weight = 0;
            }
        }
        
        return -1;
    }
}