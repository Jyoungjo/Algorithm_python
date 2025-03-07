import java.util.*;

class Node {
    int idx, parentIdx;
    long cnt;
    
    public Node(int idx, int parentIdx, long cnt) {
        this.idx = idx;
        this.parentIdx = parentIdx;
        this.cnt = cnt;
    }
}

class Solution {
    List<List<Integer>> tree = new ArrayList<>();
    boolean[] visited;
    long[] b;
    int size;
    
    public long solution(int[] a, int[][] edges) {
        init(a, edges);
        return dfs(getLeafNode(0));
    }
    
    private long dfs(int start) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(start, start, 0));
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (!visited[current.idx]) {
                visited[current.idx] = true;
                stack.push(current);                
                
                for (int next : tree.get(current.idx)) {
                    if (visited[next]) continue;
                    stack.push(new Node(next, current.idx, 0));
                }
            } else {
                // visited[current.idx] = false;
                if (current.idx == start) {
                    if (b[current.idx] != 0) return -1;
                    return current.cnt + Math.abs(b[current.idx]);
                }
                
                long weight = b[current.idx];
                b[current.idx] = 0;
                b[current.parentIdx] += weight;
                stack.peekLast().cnt += Math.abs(weight);
            }
        }
        return -1;
    }
    
    private int getLeafNode(int start) {
        visited[start] = true;
        
        for (int next : tree.get(start)) {
            if (visited[next]) continue;
            int idx = getLeafNode(next);
            visited[start] = false;
            return idx;
        }
        
        visited[start] = false;
        return start;
    }
    
    private void init(int[] a, int[][] edges) {
        size = a.length;
        visited = new boolean[size];
        b = new long[size];
        for (int i = 0; i < size; i++) {
            b[i] = a[i];
            tree.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
    }
}