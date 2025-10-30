import java.util.*;

class Node {
    int id, cnt;
    
    Node(int id, int cnt) {
        this.id = id;
        this.cnt = cnt;
    }
}

class Solution {
    int N;
    List<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        init(n, edge);
        return bfs(1);
    }
    
    private int bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(start, 0));
        visited[start] = true;
        
        int maxCnt = Integer.MIN_VALUE, answer = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            boolean isLeaf = true;
            
            for (int nxt : graph[now.id]) {
                if (visited[nxt]) continue;
                
                q.add(new Node(nxt, now.cnt + 1));
                visited[nxt] = true;
                isLeaf = false;
            }
            
            if (isLeaf) {
                if (now.cnt > maxCnt) {
                    maxCnt = now.cnt;
                    answer = 1;
                } else if (now.cnt == maxCnt) answer++;
            }
        }
        
        return answer;
    }
    
    private void init(int n, int[][] edge) {
        N = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
    }
}